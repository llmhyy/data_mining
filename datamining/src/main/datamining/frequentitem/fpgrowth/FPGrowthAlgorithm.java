package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import datamining.frequentitem.IItem;
import datamining.frequentitem.ItemSet;
import datamining.frequentitem.ItemSetComparator;
import datamining.frequentitem.Transaction;
import datamining.util.Utils;

public class FPGrowthAlgorithm {
	public ArrayList<ItemSet> runLargest(ArrayList<Transaction> transcations, int supportThreshold){
		ArrayList<ItemSet> sets = runAll(transcations, supportThreshold);
		
		Iterator<ItemSet> iterator = sets.iterator();
		while(iterator.hasNext()){
			ItemSet subItemSet = iterator.next();
			ItemSet superSet = findSuperItemSet(subItemSet, sets);
			
			if(superSet != null){
				iterator.remove();
			}
		}
		
		return sets;
	}
	
	public ArrayList<ItemSet> runLargestBySupport(ArrayList<Transaction> transcations, int supportThreshold){
		ArrayList<ItemSet> sets = runAll(transcations, supportThreshold);
		
		Iterator<ItemSet> iterator = sets.iterator();
		while(iterator.hasNext()){
			ItemSet subItemSet = iterator.next();
			ItemSet superSet = findSuperItemSet(subItemSet, sets);
			
			if(superSet != null && superSet.getSupport() >= subItemSet.getSupport()){
				iterator.remove();
			}
		}
		
		return sets;
	}
	
	
	
	private ItemSet findSuperItemSet(ItemSet subItemSet, ArrayList<ItemSet> sets){
		for(ItemSet itemSet: sets){
			if(itemSet.contains(subItemSet) && itemSet.size()>subItemSet.size()){
				return itemSet;
			}
		}
		
		return null;
	}
	
	/**
	 * The reported frequent item set will be sorted in descending order.
	 * @param transcations
	 * @param supportThreshold
	 * @return
	 */
	public ArrayList<ItemSet> runAll(ArrayList<Transaction> transcations, int supportThreshold){
		ItemHeaderTable itemHeaderTable = constructItemHeaderTable(transcations, supportThreshold);
		TreeNode fpTree = constructFPTree(itemHeaderTable, transcations, supportThreshold);
		
		ArrayList<ItemSet> sets = generateItemSet(itemHeaderTable, fpTree, supportThreshold);
		
		Collections.sort(sets, new ItemSetComparator());
		
		return sets;
	}

	private ArrayList<ItemSet> generateItemSet(ItemHeaderTable itemHeaderTable,
			TreeNode fpTree, int supportThreshold) {
		ArrayList<ItemSet> sets = new ArrayList<>();
		for(int i=0; i<itemHeaderTable.size(); i++){
			//decreasing order
			ItemHeader header = itemHeaderTable.getTable().get(itemHeaderTable.size()-1-i);
			IItem item = header.getItem();
			
			ArrayList<TreePath> pathes = fpTree.findPrefixTreePathes(header);
			if(pathes.size() == 0){
				/**
				 * the prefix is empty, thus do nothing
				 */
			}
			else if(pathes.size() == 1){
				TreePath path = pathes.get(0);
				if(path.getCount() >= supportThreshold){
					
					System.currentTimeMillis();
//					ItemSet set = new ItemSet();
//					set.addItem(item);
//					for(TreeNode node: path.getNodes()){
//						set.addItem(node.getItem());
//					}
//					set.setSupport(path.getCount());
//					sets.add(set);
					
					ArrayList<IItem> itemList = convertToItemList(path.getNodes());
					itemList.add(item);
					ArrayList<ArrayList<? extends Object>> subSets = Utils.calculateSubSets(itemList, itemList.size());
					ArrayList<ItemSet> combinatorialSets = convertToItemSet(subSets, path.getCount());
					sets.addAll(combinatorialSets);
					
					System.currentTimeMillis();
				}
			}
			else{
				//recursive
				ArrayList<Transaction> subTransactions = deriveConditionalTranscation(pathes);
				ArrayList<ItemSet> frequentItemSet = runAll(subTransactions, supportThreshold);
				for(ItemSet set0: frequentItemSet){
					set0.addItem(item);
				}
				sets.addAll(frequentItemSet);
			}
			
		}
		
		ArrayList<ItemSet> singleElementItemSets = enumerateSingleElementItemSet(itemHeaderTable);
		sets.addAll(singleElementItemSets);
		
		
		return sets;
	}
	
	private ArrayList<IItem> convertToItemList(ArrayList<TreeNode> nodeList){
		ArrayList<IItem> itemList = new ArrayList<>();
		for(TreeNode node: nodeList){
			itemList.add(node.getItem());
		}
		return itemList;
	}
	
	private ArrayList<ItemSet> convertToItemSet(
			ArrayList<ArrayList<? extends Object>> subSets, int support) {
		ArrayList<ItemSet> itemSetList = new ArrayList<>();
		for(ArrayList<? extends Object> subset: subSets){
			if(subset.size() > 1){
				ItemSet itemSet = new ItemSet();
				for(Object obj: subset){
					itemSet.addItem((IItem)obj);
					itemSet.setSupport(support);
				}
				itemSetList.add(itemSet);					
			}
		}
		
		return itemSetList;
	}

	private ArrayList<ItemSet> enumerateSingleElementItemSet(ItemHeaderTable table){
		ArrayList<ItemSet> list = new ArrayList<>();
		for(ItemHeader header: table.getTable()){
			ItemSet set = new ItemSet();
			set.addItem(header.getItem());
			set.setSupport(header.getCount());
			list.add(set);
		}
		
		return list;
	}

	private ArrayList<Transaction> deriveConditionalTranscation(ArrayList<TreePath> pathes) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		for(TreePath path: pathes){
			ArrayList<IItem> itemList = new ArrayList<>();
			for(TreeNode node: path.getNodes()){
				itemList.add(node.getItem());
			}
			
			for(int i=0; i<path.getCount(); i++){
				Transaction transaction = new Transaction(itemList);
				transactions.add(transaction);
			}
		}
		
		return transactions;
	}

	private TreeNode constructFPTree(ItemHeaderTable itemHeaderTable,
			ArrayList<Transaction> transcations, int supportThreshold) {
		TreeNode root = new TreeNode(null);
		for(Transaction transcation: transcations){
			ArrayList<IItem> itemList = transcation.getItemList(); 
			cleanNonfrequentItem(itemList, itemHeaderTable, supportThreshold);
			Collections.sort(itemList, new ItemCountComparator(itemHeaderTable));
			
			TreeNode parentNode = root;
			for(IItem item: itemList){
				TreeNode node = parentNode.findInDirectChildren(item);
				if(node == null){
					node = new TreeNode(item, 1);
					parentNode.addChild(node);
					node.setParent(parentNode);
					
					ItemHeader header = itemHeaderTable.find(item);
					header.addNodeLink(node);
				}
				else{
					node.setCount(node.getCount()+1);
				}
				
				
				parentNode = node;
			}
		}
		
		return root;
	}
	
	

	private void cleanNonfrequentItem(ArrayList<IItem> itemList, ItemHeaderTable itemHeaderTable,
			int supportThreshold) {
		Iterator<IItem> iterator = itemList.iterator();
		while(iterator.hasNext()){
			IItem item = iterator.next();
			if(itemHeaderTable.find(item) == null){
				iterator.remove();
			}
		}
	}

	private ItemHeaderTable constructItemHeaderTable(
			ArrayList<Transaction> transcations, int supportThreshold) {
		ItemHeaderTable table = new ItemHeaderTable();
		for(Transaction transcation: transcations){
			for(IItem item: transcation.getItemList()){
				ItemHeader header = table.find(item);
				if(null == header){
					header = new ItemHeader(item, 1);
					table.addHeader(header);
				}
				else{
					header.setCount(header.getCount()+1);
				}
			}
		}
		
		table.retainFrequentItems(supportThreshold);
		table.rank();
		
		return table;
	}
}

package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import datamining.frequentitem.Item;
import datamining.frequentitem.ItemSet;
import datamining.frequentitem.Transaction;

public class FPGrowthAlgorithm {
	
	
	public ArrayList<ItemSet> run(ArrayList<Transaction> transcations, int supportThreshold){
		ItemHeaderTable itemHeaderTable = constructItemHeaderTable(transcations, supportThreshold);
		TreeNode fpTree = constructFPTree(itemHeaderTable, transcations, supportThreshold);
		
		ArrayList<ItemSet> set = generateItemSet(itemHeaderTable, fpTree, supportThreshold);
		
		return set;
	}

	private ArrayList<ItemSet> generateItemSet(ItemHeaderTable itemHeaderTable,
			TreeNode fpTree, int supportThreshold) {
		ArrayList<ItemSet> sets = new ArrayList<>();
		for(int i=0; i<itemHeaderTable.size(); i++){
			//decreasing order
			ItemHeader header = itemHeaderTable.getTable().get(itemHeaderTable.size()-1-i);
			Item item = header.getItem();
			
			ArrayList<TreePath> pathes = fpTree.findPrefixTreePathes(header);
			if(pathes.size() == 0){
				/**
				 * the prefix is empty, thus do nothing
				 */
			}
			else if(pathes.size() == 1){
				TreePath path = pathes.get(0);
				if(path.getCount() >= supportThreshold){
					ItemSet set = new ItemSet();
					set.addItem(item);
					for(TreeNode node: path.getNodes()){
						set.addItem(node.getItem());
					}
					set.setSupport(path.getCount());
					sets.add(set);
					
					//TODO
					//sets.addAll(combinatorialSets);
				}
			}
			else{
				//recursive
				ArrayList<Transaction> subTransactions = deriveConditionalTranscation(pathes);
				ArrayList<ItemSet> frequentItemSet = run(subTransactions, supportThreshold);
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
			ArrayList<Item> itemList = new ArrayList<>();
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
			ArrayList<Item> itemList = transcation.getItemList(); 
			cleanNonfrequentItem(itemList, itemHeaderTable, supportThreshold);
			Collections.sort(itemList, new ItemCountComparator(itemHeaderTable));
			
			TreeNode parentNode = root;
			for(Item item: itemList){
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
	
	

	private void cleanNonfrequentItem(ArrayList<Item> itemList, ItemHeaderTable itemHeaderTable,
			int supportThreshold) {
		Iterator<Item> iterator = itemList.iterator();
		while(iterator.hasNext()){
			Item item = iterator.next();
			if(itemHeaderTable.find(item) == null){
				iterator.remove();
			}
		}
	}

	private ItemHeaderTable constructItemHeaderTable(
			ArrayList<Transaction> transcations, int supportThreshold) {
		ItemHeaderTable table = new ItemHeaderTable();
		for(Transaction transcation: transcations){
			for(Item item: transcation.getItemList()){
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

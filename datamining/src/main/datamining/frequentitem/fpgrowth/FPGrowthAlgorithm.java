package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;
import java.util.Collections;

import datamining.frequentitem.Item;
import datamining.frequentitem.ItemSet;
import datamining.frequentitem.Transaction;

public class FPGrowthAlgorithm {
	
	
	public ArrayList<ItemSet> run(ArrayList<Transaction> transcations, int support){
		ItemHeaderTable itemHeaderTable = constructItemHeaderTable(transcations, support);
		TreeNode fpTree = constructFPTree(itemHeaderTable, transcations);
		
		ArrayList<ItemSet> set = generateItemSet(itemHeaderTable, fpTree, support);
		
		return null;
	}

	private ArrayList<ItemSet> generateItemSet(ItemHeaderTable itemHeaderTable,
			TreeNode fpTree, int support) {
		// TODO Auto-generated method stub
		return null;
	}

	private TreeNode constructFPTree(ItemHeaderTable itemHeaderTable,
			ArrayList<Transaction> transcations) {
		TreeNode root = new TreeNode(null);
		for(Transaction transcation: transcations){
			ArrayList<Item> itemList = transcation.getItemList(); 
			Collections.sort(itemList, new ItemCountComparator(itemHeaderTable));
			
			TreeNode parentNode = root;
			for(Item item: itemList){
				TreeNode node = parentNode.findInDirectChildren(item);
				if(node == null){
					node = new TreeNode(item, 1);
					parentNode.addChild(node);
					
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

	private ItemHeaderTable constructItemHeaderTable(
			ArrayList<Transaction> transcations, int support) {
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
		
		table.retainFrequentItems(support);
		table.rank();
		
		return table;
	}
}

package datamining.frequentitem;

import java.util.ArrayList;

public class Transaction {
	private ArrayList<IItem> itemList = new ArrayList<>();
	
	public Transaction(ArrayList<IItem> itemList) {
		this.itemList = itemList;
	}
	
	public String toString(){
		return itemList.toString();
	}

	public void addItem(IItem item){
		this.itemList.add(item);
	}

	/**
	 * @return the itemList
	 */
	public ArrayList<IItem> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(ArrayList<IItem> itemList) {
		this.itemList = itemList;
	}
	
	public boolean contains(ItemSet itemSet){
		for(IItem item: itemSet.getItemList()){
			if(!this.itemList.contains(item)){
				return false;
			}
		}
		return true;
	}
	
}

package datamining.frequentitem;

import java.util.ArrayList;

public class Transaction {
	private ArrayList<Item> itemList = new ArrayList<>();
	
	public Transaction(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	public String toString(){
		return itemList.toString();
	}

	public void addItem(Item item){
		this.itemList.add(item);
	}

	/**
	 * @return the itemList
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}

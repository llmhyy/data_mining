package datamining.frequentitem;

import java.util.ArrayList;

public class ItemSet {
	private ArrayList<Item> itemList = new ArrayList<>();
	private int support;
	
	public String toString(){
		return itemList.toString();
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
	
	public void addItem(Item item){
		this.itemList.add(item);
	}
	
	/**
	 * @return the support
	 */
	public int getSupport() {
		return support;
	}
	/**
	 * @param support the support to set
	 */
	public void setSupport(int support) {
		this.support = support;
	}
	
	
}

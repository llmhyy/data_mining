package datamining.frequentitem;

import java.util.ArrayList;

public class ItemSet {
	private ArrayList<IItem> itemList = new ArrayList<>();
	private int support;
	
	public String toString(){
		return itemList.toString();
	}
	
	public boolean contains(ItemSet set){
		for(IItem thatItem: set.getItemList()){
			if(!this.itemList.contains(thatItem)){
				return false;
			}
		}
		
		return true;
	}
	
	public int size(){
		return this.itemList.size();
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
	
	public void addItem(IItem item){
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

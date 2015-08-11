package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;

import datamining.frequentitem.Item;

/**
 * @author linyun
 *
 */
public class ItemHeader {
	private Item item;
	private int count;
	private ArrayList<TreeNode> nodeLinks = new ArrayList<>();
	
	/**
	 * @param item
	 * @param count
	 */
	public ItemHeader(Item item, int count) {
		super();
		this.item = item;
		this.count = count;
	}
	
	public String toString(){
		return item.toString() + "(" + this.count + ")";
	}
	
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the nodeLinks
	 */
	public ArrayList<TreeNode> getNodeLinks() {
		return nodeLinks;
	}
	/**
	 * @param nodeLinks the nodeLinks to set
	 */
	public void setNodeLinks(ArrayList<TreeNode> nodeLinks) {
		this.nodeLinks = nodeLinks;
	}
	
	public void addNodeLink(TreeNode node){
		this.nodeLinks.add(node);
	}
	
}

package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;

import datamining.frequentitem.Item;

/**
 * @author linyun
 *
 */
public class TreeNode {
	private Item item;
	private int count;
	
	private TreeNode parent;
	private ArrayList<TreeNode> children = new ArrayList<>();
	
	public TreeNode(){}
	
	public TreeNode(Item item){
		this.item = item;
	}
	
	public TreeNode(Item item, int count){
		this.item = item;
		this.count = count;
	}
	
	public String toString(){
		String itemString = (item == null) ? "null" : item.toString();
		return itemString + "(" + count + ")";
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
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}
	
	public void addChild(TreeNode node){
		this.children.add(node);
	}

	public TreeNode findInDirectChildren(Item item) {
		for(TreeNode child: this.children){
			if(child.getItem().equals(item)){
				return child;
			}
		}
		
		return null;
	}
}

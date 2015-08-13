package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;

import datamining.frequentitem.IItem;

/**
 * @author linyun
 *
 */
public class TreeNode {
	private IItem item;
	private int count;
	
	private TreeNode parent;
	private ArrayList<TreeNode> children = new ArrayList<>();
	
	public TreeNode(){}
	
	public TreeNode(IItem item){
		this.item = item;
	}
	
	public TreeNode(IItem item, int count){
		this.item = item;
		this.count = count;
	}
	
	public String toString(){
		String itemString = (item == null) ? "null" : item.toString();
		return itemString + "(" + count + ")";
	}
	
	public boolean isRoot(){
		return this.item == null;
	}
	
	/**
	 * @return the item
	 */
	public IItem getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(IItem item) {
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

	public TreeNode findInDirectChildren(IItem item) {
		for(TreeNode child: this.children){
			if(child.getItem().equalItem(item)){
				return child;
			}
		}
		
		return null;
	}

	public ArrayList<TreePath> findPrefixTreePathes(ItemHeader header) {
		ArrayList<TreePath> pathList = new ArrayList<>();
		for(TreeNode node: header.getNodeLinks()){
			TreePath path = new TreePath();
			
			TreeNode n = node;
			while(!n.isRoot()){
				if(!header.getItem().equalItem(n.getItem())){
					path.addNode(n);					
				}
				n = n.getParent();
			}
			path.setCount(node.getCount());
			
			if(path.getNodes().size() != 0){
				pathList.add(path);				
			}
		}
		
		return pathList;
	}
}

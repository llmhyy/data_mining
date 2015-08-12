package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;

public class TreePath {
	private ArrayList<TreeNode> nodes = new ArrayList<>();
	private int count;
	
	public String toString(){
		return nodes.toString() + "(" + count + ")";
	}
	
	/**
	 * @return the nodes
	 */
	public ArrayList<TreeNode> getNodes() {
		return nodes;
	}
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(ArrayList<TreeNode> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(TreeNode node){
		this.nodes.add(node);
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
	
	
}

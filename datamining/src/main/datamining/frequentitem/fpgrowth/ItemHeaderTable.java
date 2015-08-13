package datamining.frequentitem.fpgrowth;

import java.util.ArrayList;
import java.util.Iterator;

import datamining.frequentitem.IItem;

public class ItemHeaderTable {
	private ArrayList<ItemHeader> table = new ArrayList<>();

	public String toString(){
		return table.toString();
	}
	
	/**
	 * @return the table
	 */
	public ArrayList<ItemHeader> getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(ArrayList<ItemHeader> table) {
		this.table = table;
	}
	
	public int size(){
		return this.table.size();
	}

	public ItemHeader find(IItem item) {
		for(ItemHeader header: this.table){
			if(header.getItem().equals(item)){
				return header;
			}
		}
		
		return null;
	}

	public void addHeader(ItemHeader header) {
		this.table.add(header);
	}

	public void retainFrequentItems(int support) {
		Iterator<ItemHeader> iterator = this.table.iterator();
		while(iterator.hasNext()){
			ItemHeader header = iterator.next();
			if(header.getCount() < support){
				iterator.remove();
			}
		}
	}

	public void rank() {
		for(int i=table.size()-1; i>=0; i--){
			for(int j=0; j<i; j++){
				if(table.get(j).getCount() < table.get(j+1).getCount()){
					ItemHeader tmp = table.get(j);
					table.set(j, table.get(j+1));
					table.set(j+1, tmp);
				}
			}
		}
		
	}
	
	
}

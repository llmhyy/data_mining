package datamining.frequentitem.fpgrowth;

import java.util.Comparator;

import datamining.frequentitem.Item;

public class ItemCountComparator implements Comparator<Item> {

	private ItemHeaderTable table;
	
	public ItemCountComparator(ItemHeaderTable table){
		this.table = table;
	}
	
	@Override
	public int compare(Item arg0, Item arg1) {
		return table.find(arg1).getCount() - table.find(arg0).getCount();
	}

}

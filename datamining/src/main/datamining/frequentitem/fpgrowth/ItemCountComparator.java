package datamining.frequentitem.fpgrowth;

import java.util.Comparator;

import datamining.frequentitem.IItem;

public class ItemCountComparator implements Comparator<IItem> {

	private ItemHeaderTable table;
	
	public ItemCountComparator(ItemHeaderTable table){
		this.table = table;
	}
	
	@Override
	public int compare(IItem arg0, IItem arg1) {
		return table.find(arg1).getCount() - table.find(arg0).getCount();
	}

}

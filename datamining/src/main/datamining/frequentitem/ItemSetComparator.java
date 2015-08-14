package datamining.frequentitem;

import java.util.Comparator;

public class ItemSetComparator implements Comparator<ItemSet> {

	@Override
	public int compare(ItemSet arg0, ItemSet arg1) {
		return arg1.getSupport() - arg0.getSupport();
	}

}

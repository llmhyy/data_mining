package datamining.util;

import java.util.ArrayList;
import java.util.Iterator;

public class Utils {
	
	public static ArrayList<ArrayList<? extends Object>> calculateSubSets(ArrayList<? extends Object> list, int n){
		ArrayList<ArrayList<? extends Object>> subsets = new ArrayList<>();
		for(int i=1; i<=n; i++){
			ArrayList<ArrayList<? extends Object>> subSubsets = calculate(list, i);
			subsets.addAll(subSubsets);
		}
		return subsets;
	}

	public static ArrayList<ArrayList<? extends Object>> calculate(ArrayList<? extends Object> list, int n) {
		if(n == 1){
			ArrayList<ArrayList<? extends Object>> singleElementSubsets = new ArrayList<>();
			for(Object obj: list){
				ArrayList<Object> subSet = new ArrayList<>();
				subSet.add(obj);
				singleElementSubsets.add(subSet);
			}
			return singleElementSubsets;
		}
		else{
			ArrayList<ArrayList<? extends Object>> newSubsets = new ArrayList<>();
			ArrayList<ArrayList<? extends Object>> previousSubsets = calculate(list, n-1);
			for(ArrayList<? extends Object> previousSubset: previousSubsets){
				for(Object obj: list){
					if(!previousSubset.contains(obj)){
						ArrayList<Object> newSubset = new ArrayList<>();
						newSubset.addAll(previousSubset);
						newSubset.add(obj);
						
						if(!isContain(newSubsets, newSubset)){
							newSubsets.add(newSubset);							
						}
					}
				}
			}
			
			return newSubsets;
		}
	}

	private static boolean isContain(ArrayList<ArrayList<? extends Object>> constructedSubSets,
			ArrayList<Object> unifiedSubSet) {
		for(ArrayList<? extends Object> subset: constructedSubSets){
			if(isSameList(subset, unifiedSubSet)){
				return true;
			}
		}
		return false;
	}

	private static boolean isSameList(ArrayList<? extends Object> subset,
			ArrayList<Object> unifiedSubSet) {
		ArrayList<Object> clonedSubset = cloneList(subset);
		ArrayList<Object> clonedUnifiedSubset = cloneList(unifiedSubSet);
		
		Iterator<Object> iterator1 = clonedSubset.iterator();
		while(iterator1.hasNext()){
			Object obj1 = iterator1.next();
			
			Iterator<Object>  iterator2 = clonedUnifiedSubset.iterator();
			while(iterator2.hasNext()){
				Object obj2 = iterator2.next();
				
				if(obj1.equals(obj2)){
					iterator1.remove();
					iterator2.remove();
				}
			}
		}

		return clonedSubset.size() == 0 && clonedUnifiedSubset.size() == 0;
	}
	
	private static ArrayList<Object> cloneList(ArrayList<? extends Object> list){
		ArrayList<Object> clonedList = new ArrayList<>();
		for(Object obj: list){
			clonedList.add(obj);
		}
		return clonedList;
	}
}

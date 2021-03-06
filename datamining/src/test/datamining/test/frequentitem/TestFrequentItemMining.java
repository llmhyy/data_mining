package datamining.test.frequentitem;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import datamining.frequentitem.IItem;
import datamining.frequentitem.ItemSet;
import datamining.frequentitem.Transaction;
import datamining.frequentitem.fpgrowth.FPGrowthAlgorithm;

public class TestFrequentItemMining {
	
	ArrayList<Transaction> transList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		
		ArrayList<IItem> list0 = new ArrayList<>();
		list0.add(new Char('A'));
		list0.add(new Char('B'));
		list0.add(new Char('E'));
		Transaction transaction0 = new Transaction(list0);
		transList.add(transaction0);
		
		ArrayList<IItem> list1 = new ArrayList<>();
		list1.add(new Char('B'));
		list1.add(new Char('D'));
		Transaction transaction1 = new Transaction(list1);
		transList.add(transaction1);
		
		ArrayList<IItem> list2 = new ArrayList<>();
		list2.add(new Char('B'));
		list2.add(new Char('C'));
		Transaction transaction2 = new Transaction(list2);
		transList.add(transaction2);
		
		ArrayList<IItem> list3 = new ArrayList<>();
		list3.add(new Char('A'));
		list3.add(new Char('B'));
		list3.add(new Char('D'));
		Transaction transaction3 = new Transaction(list3);
		transList.add(transaction3);
		
		ArrayList<IItem> list4 = new ArrayList<>();
		list4.add(new Char('A'));
		list4.add(new Char('C'));
		Transaction transaction4 = new Transaction(list4);
		transList.add(transaction4);
		
		ArrayList<IItem> list5 = new ArrayList<>();
		list5.add(new Char('B'));
		list5.add(new Char('C'));
		Transaction transaction5 = new Transaction(list5);
		transList.add(transaction5);
		
		ArrayList<IItem> list6 = new ArrayList<>();
		list6.add(new Char('A'));
		list6.add(new Char('C'));
		Transaction transaction6 = new Transaction(list6);
		transList.add(transaction6);
		
		ArrayList<IItem> list7 = new ArrayList<>();
		list7.add(new Char('A'));
		list7.add(new Char('B'));
		list7.add(new Char('C'));
		list7.add(new Char('E'));
		Transaction transaction7 = new Transaction(list7);
		transList.add(transaction7);
		
		ArrayList<IItem> list8 = new ArrayList<>();
		list8.add(new Char('A'));
		list8.add(new Char('B'));
		list8.add(new Char('C'));
		Transaction transaction8 = new Transaction(list8);
		transList.add(transaction8);
	}

	@Test
	public void testRunAll() {
		FPGrowthAlgorithm algo = new FPGrowthAlgorithm();
		ArrayList<ItemSet> sets = algo.runAll(transList, 2);
		
		Assert.assertTrue(sets.toString().equals("[[B], [A], [C], [B, C], [A, C], "
				+ "[B, A], [B, D], [A, B, E], [A, E], [B, E], [A, B, C], [E], [D]]"));
		
		System.currentTimeMillis();
	}

	
	@Test
	public void testRunLargest() {
		FPGrowthAlgorithm algo = new FPGrowthAlgorithm();
		ArrayList<ItemSet> sets = algo.runLargest(transList, 2);
		
		Assert.assertTrue(sets.toString().equals("[[B, D], [A, B, E], [A, B, C]]"));
		
		System.currentTimeMillis();
	}
	
	@Test
	public void testRunLargestBySupport() {
		FPGrowthAlgorithm algo = new FPGrowthAlgorithm();
		ArrayList<ItemSet> sets = algo.runLargestBySupport(transList, 2);
		
		Assert.assertTrue(sets.toString().equals("[[B], [A], [C], [B, C], [A, C], [B, A], "
				+ "[B, D], [A, B, E], [A, B, C]]"));
		
		System.currentTimeMillis();
	}

}

package datamining.test.frequentitem;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datamining.frequentitem.Item;
import datamining.frequentitem.Transaction;
import datamining.frequentitem.fpgrowth.FPGrowthAlgorithm;

public class TestFrequentItemMining {
	
	ArrayList<Transaction> transList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		
		ArrayList<Item> list0 = new ArrayList<>();
		list0.add(new Char('A'));
		list0.add(new Char('B'));
		list0.add(new Char('E'));
		Transaction transaction0 = new Transaction(list0);
		transList.add(transaction0);
		
		ArrayList<Item> list1 = new ArrayList<>();
		list1.add(new Char('B'));
		list1.add(new Char('D'));
		Transaction transaction1 = new Transaction(list1);
		transList.add(transaction1);
		
		ArrayList<Item> list2 = new ArrayList<>();
		list2.add(new Char('B'));
		list2.add(new Char('C'));
		Transaction transaction2 = new Transaction(list2);
		transList.add(transaction2);
		
		ArrayList<Item> list3 = new ArrayList<>();
		list3.add(new Char('A'));
		list3.add(new Char('B'));
		list3.add(new Char('D'));
		Transaction transaction3 = new Transaction(list3);
		transList.add(transaction3);
		
		ArrayList<Item> list4 = new ArrayList<>();
		list4.add(new Char('A'));
		list4.add(new Char('C'));
		Transaction transaction4 = new Transaction(list4);
		transList.add(transaction4);
		
		ArrayList<Item> list5 = new ArrayList<>();
		list5.add(new Char('B'));
		list5.add(new Char('C'));
		Transaction transaction5 = new Transaction(list5);
		transList.add(transaction5);
		
		ArrayList<Item> list6 = new ArrayList<>();
		list6.add(new Char('A'));
		list6.add(new Char('C'));
		Transaction transaction6 = new Transaction(list6);
		transList.add(transaction6);
		
		ArrayList<Item> list7 = new ArrayList<>();
		list7.add(new Char('A'));
		list7.add(new Char('B'));
		list7.add(new Char('C'));
		list7.add(new Char('E'));
		Transaction transaction7 = new Transaction(list7);
		transList.add(transaction7);
		
		ArrayList<Item> list8 = new ArrayList<>();
		list8.add(new Char('A'));
		list8.add(new Char('B'));
		list8.add(new Char('C'));
		Transaction transaction8 = new Transaction(list8);
		transList.add(transaction8);
	}

	@Test
	public void test() {
		FPGrowthAlgorithm algo = new FPGrowthAlgorithm();
		algo.run(transList, 2);
	}

}

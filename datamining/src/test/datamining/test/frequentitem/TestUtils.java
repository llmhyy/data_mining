package datamining.test.frequentitem;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datamining.util.Utils;

public class TestUtils {
	
	ArrayList<Char> set = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		set.add(new Char('A'));
		set.add(new Char('B'));
		set.add(new Char('C'));
		set.add(new Char('D'));
	}

	@Test
	public void test() {
		ArrayList<ArrayList<? extends Object>> subsets = Utils.calculateSubSets(set, set.size());
		
		assertTrue(subsets.toString().equals(
				"[[A], [B], [C], [D], "
				+ "[A, B], [A, C], [A, D], [B, C], [B, D], [C, D], "
				+ "[A, B, C], [A, B, D], [A, C, D], [B, C, D], "
				+ "[A, B, C, D]]"));		
	}

}

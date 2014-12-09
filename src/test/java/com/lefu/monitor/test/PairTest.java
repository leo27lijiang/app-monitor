package com.lefu.monitor.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lefu.monitor.Pair;

import junit.framework.TestCase;

public class PairTest extends TestCase {
	private Set<Pair> sets;
	
	@Before
	public void setUp() {
		sets = new HashSet<Pair>();
	}
	
	@Test
	public void testPairInSet() {
		sets.add(null);
		assertEquals(1, sets.size());
		sets.add(null);
		assertEquals(1, sets.size());
		Pair pair = new Pair(1);
		sets.add(pair);
		pair = new Pair(2);
		sets.add(pair);
		assertEquals(2, sets.size());
		pair = new Pair("3", 3);
		sets.add(pair);
		pair = new Pair("3", 4);
		sets.add(pair);
		assertEquals(3, sets.size());
		for (Pair p : sets) {
			System.out.println(p.getValue());
		}
	}
	
	@After
	public void tearDown() {
		sets.clear();
	}
	
}

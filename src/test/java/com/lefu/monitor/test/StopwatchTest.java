package com.lefu.monitor.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lefu.monitor.util.Stopwatch;
import com.lefu.monitor.util.ThreadStopwatch;

import junit.framework.TestCase;

public class StopwatchTest extends TestCase {
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testTime() {
		Stopwatch stopwatch = new ThreadStopwatch();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopwatch = new ThreadStopwatch();
		assertTrue(stopwatch.get() >= 5000);
	}
	
	@After
	public void tearDown() {
		
	}
	
}

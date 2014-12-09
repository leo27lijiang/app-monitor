package com.lefu.monitor.util;

/**
 * 计时器
 * @author jiang.li
 *
 */
public class SimpleStopwatch implements Stopwatch {
	private long start;
	
	public SimpleStopwatch() {
		mark();
	}
	
	@Override
	public void mark() {
		this.start = System.currentTimeMillis();
	}
	
	@Override
	public long get() {
		return System.currentTimeMillis() - this.start;
	}
	
}

package com.lefu.monitor.util;

/**
 * 计时器
 * @author jiang.li
 *
 */
public interface Stopwatch {
	/**
	 * 标记时间
	 */
	public void mark();
	/**
	 * 获取此时与mark之间的时差，如果之前不曾调用过mark则返回0或者是当前时间与实例的创建时间之差
	 * @return
	 */
	public long get();
	
}

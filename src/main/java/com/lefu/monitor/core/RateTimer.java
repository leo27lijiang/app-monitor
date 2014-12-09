package com.lefu.monitor.core;

public interface RateTimer {
	/**
	 * 默认的统计间隔，60秒
	 */
	public int DEFAULT_RATE = 60;
	/**
	 * 触发指定的闹钟
	 * @param clockName
	 */
	public void trriger(String clockName);
	
}

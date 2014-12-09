package com.lefu.monitor.core.format;

import java.util.List;

import com.lefu.monitor.StatisticSet;

public interface StatisticFormat {
	/**
	 * 转换为字符串形式
	 * @param set
	 * @return
	 */
	public String format(StatisticSet set);
	/**
	 * 
	 * @param sets
	 * @return
	 */
	public String format(List<StatisticSet> sets);
}

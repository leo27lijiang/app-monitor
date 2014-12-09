package com.lefu.monitor;

import java.util.List;

import com.lefu.monitor.core.format.StatisticFormat;

/**
 * 通道
 * @author jiang.li
 *
 */
public interface Way {
	/**
	 * push指定的指标数据
	 * @param data
	 */
	public void push(StatisticSet data);
	/**
	 * push集合数据
	 * @param datas
	 */
	public void push(List<StatisticSet> datas);
	/**
	 * 设定数据转换格式
	 * @param format
	 */
	public void setStatisticFormat(StatisticFormat format);
}

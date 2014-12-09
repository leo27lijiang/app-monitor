package com.lefu.monitor.core.complex;

import com.lefu.monitor.StatisticSet;

/**
 * 指示复合结构可以转换为指标结构
 * @author jiang.li
 *
 */
public interface Complex {
	/**
	 * 一次转换后是否重置数据
	 * @return
	 */
	public boolean isGetWithReset();
	/**
	 * 转换为指标结构
	 * @return
	 */
	public StatisticSet toStatisticSet();
	
}

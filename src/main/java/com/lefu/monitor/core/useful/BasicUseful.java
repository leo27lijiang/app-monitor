package com.lefu.monitor.core.useful;

import com.lefu.monitor.Pair;
import com.lefu.monitor.StatisticSet;
import com.lefu.monitor.Useful;
import com.lefu.monitor.Way;

/**
 * 保持原始数据的工具类
 * @author jiang.li
 *
 */
public class BasicUseful implements Useful {
	private final Way way;
	
	public BasicUseful(Way way) {
		this.way = way;
	}
	
	@Override
	public void putData(String name, Pair... pairs) {
		if (way != null) this.way.push(new StatisticSet(name, pairs));
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

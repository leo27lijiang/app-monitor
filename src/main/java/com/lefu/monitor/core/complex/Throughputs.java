package com.lefu.monitor.core.complex;

import java.util.concurrent.atomic.AtomicInteger;

import com.lefu.monitor.Pair;
import com.lefu.monitor.StatisticSet;

/**
 * 吞吐类基础数据结构
 * @author jiang.li
 *
 */
public class Throughputs implements Complex {
	private final String name;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public Throughputs(String name) {
		this.name = name;
	}
	
	public void put() {
		counter.getAndIncrement();
	}

	@Override
	public boolean isGetWithReset() {
		return true;
	}

	@Override
	public StatisticSet toStatisticSet() {
		StatisticSet set = new StatisticSet();
		set.setName(name);
		set.setDatapoints(new Pair("TPS", counter.get()));
		counter.set(0);
		return set;
	}

	public String getName() {
		return name;
	}

}

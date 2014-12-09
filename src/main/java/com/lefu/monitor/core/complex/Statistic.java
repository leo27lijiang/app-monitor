package com.lefu.monitor.core.complex;

import java.util.concurrent.locks.ReentrantLock;

import com.lefu.monitor.Pair;
import com.lefu.monitor.StatisticSet;

/**
 * 统计类基础数据结构
 * @author jiang.li
 *
 */
public class Statistic implements Complex {
	private final ReentrantLock lock = new ReentrantLock();
	private final String name;
	/**
	 * 每次获取数据后，重置
	 */
	private boolean getReset = true;
	/**
	 * 初始统计
	 */
	private boolean inited = false;
	private int count;
	private double sum;
	private double avg;
	private int min;
	private int max;
	
	public Statistic(String name) {
		this.name = name;
	}
	
	public void putNum(Object... objs) {
		lock.lock();
		try {
			if (objs == null || objs.length == 0) {
				return;
			}
			for (Object v : objs) {
				int num = 0;
				if (v instanceof Integer) {
					num = (Integer) v;
				} else {
					try {
						num = Integer.parseInt(v.toString());
					} catch (NumberFormatException e) {
					}
				}
				count++;
				if (!inited) {
					min = num;
					max = num;
					inited = true;
				}
				if (min > num) {
					min = num;
				}
				if (max < num) {
					max = num;
				}
				sum += num;
			}
			if (sum > 0) {
				avg = sum / count;
			}
		} finally {
			lock.unlock();
		}
	}

	public String getName() {
		return name;
	}
	
	public void getReset(boolean v) {
		this.getReset = v;
	}

	@Override
	public boolean isGetWithReset() {
		return this.getReset;
	}
	
	@Override
	public StatisticSet toStatisticSet() {
		lock.lock();
		try {
			StatisticSet set = new StatisticSet();
			set.setName(name);
			set.setDatapoint(new Pair("count", count));
			set.setDatapoint(new Pair("sum", sum));
			set.setDatapoint(new Pair("min", min));
			set.setDatapoint(new Pair("max", max));
			set.setDatapoint(new Pair("avg", avg));
			if (isGetWithReset()) {
				count = 0;
				sum = 0;
				min = 0;
				max = 0;
				avg = 0;
			}
			return set;
		} finally {
			lock.unlock();
		}
	}
	
}

package com.lefu.monitor.core.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import com.lefu.monitor.Pair;
import com.lefu.monitor.StatisticSet;

/**
 * 占比类基础数据结构
 * @author jiang.li
 *
 */
public class Percent implements Complex {
	private final ReentrantLock lock = new ReentrantLock();
	private final String name;
	private final Map<Object, Integer> elements = new HashMap<Object, Integer>();
	private int count;
	private boolean getReset = true;
	
	public Percent(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isGetWithReset() {
		return getReset;
	}
	
	public void putData(Object... objs) {
		lock.lock();
		try {
			if (objs == null || objs.length == 0) {
				return;
			}
			for (Object obj : objs) {
				if (elements.containsKey(obj)) {
					Integer e = elements.get(obj);
					elements.put(obj, new Integer(e + 1));
				} else {
					elements.put(obj, new Integer(1));
				}
				count++;
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public StatisticSet toStatisticSet() {
		lock.lock();
		try {
			StatisticSet set = new StatisticSet();
			set.setName(name);
			set.setDatapoint(new Pair("total", count));
			for (Entry<Object, Integer> entry : elements.entrySet()) {
				set.setDatapoint(new Pair(entry.getKey().toString(), entry.getValue()));
			}
			if (getReset) {
				elements.clear();
				count = 0;
			}
			return set;
		} finally {
			lock.unlock();
		}
	}

	public String getName() {
		return name;
	}

	public void setGetReset(boolean getReset) {
		this.getReset = getReset;
	}

}

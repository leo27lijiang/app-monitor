package com.lefu.monitor.core.useful;

import java.util.Hashtable;
import java.util.Map;

import com.lefu.monitor.Pair;
import com.lefu.monitor.Useful;
import com.lefu.monitor.Way;
import com.lefu.monitor.core.RateTimer;
import com.lefu.monitor.core.Timer;
import com.lefu.monitor.core.complex.Statistic;

/**
 * 统计类的工具，以整型数据为基础
 * @author jiang.li
 *
 */
public class StatisticUseful implements Useful, RateTimer {
	private final Map<String, Statistic> metrics = new Hashtable<String, Statistic>();
	private Way way;
	private Timer timer;
	
	public StatisticUseful(Way w) {
		this.way = w;
	}
	
	public StatisticUseful(Way w, Timer timer) {
		this(w);
		this.timer = timer;
	}
	
	@Override
	public void putData(String name, Pair... pairs) {
		if (pairs == null || pairs.length == 0) {
			return;
		}
		Statistic statistic = metrics.get(name);
		if (statistic == null) {
			statistic = new Statistic(name);
			metrics.put(name, statistic);
			String propertyName = RATE_PROPERTYE_PREFIX + name;
			int rate = DEFAULT_RATE;
			try {
				rate = Integer.parseInt(System.getProperty(propertyName, String.valueOf(DEFAULT_RATE)));
			} catch (NumberFormatException e) {
			}
			this.timer.addListener(name, this, rate);
		}
		Object[] param = new Object[pairs.length];
		for (int i = 0; i < pairs.length; i++) {
			param[i] = pairs[i].getValue();
		}
		statistic.putNum(param);
	}

	@Override
	public void trriger(String clockName) {
		Statistic statistic = metrics.get(clockName);
		if (statistic == null) {
			return;
		}
		try {
			if (way != null) way.push(statistic.toStatisticSet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setWay(Way way) {
		this.way = way;
	}

}

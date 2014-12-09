package com.lefu.monitor.core.useful;

import java.util.Hashtable;
import java.util.Map;

import com.lefu.monitor.Pair;
import com.lefu.monitor.Useful;
import com.lefu.monitor.Way;
import com.lefu.monitor.core.RateTimer;
import com.lefu.monitor.core.Timer;
import com.lefu.monitor.core.complex.Percent;

/**
 * 元素占比统计类工具，以字符串数据为基础
 * @author jiang.li
 *
 */
public class PercentUseful implements Useful, RateTimer {
	private final Map<String, Percent> metrics = new Hashtable<String, Percent>();
	private Way way;
	private Timer timer;
	
	public PercentUseful(Way way) {
		this.way = way;
	}
	
	public PercentUseful(Way way, Timer timer) {
		this(way);
		this.timer = timer;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void putData(String name, Pair... pairs) {
		if (pairs == null || pairs.length == 0) {
			return;
		}
		Percent percent = metrics.get(name);
		if (percent == null) {
			percent = new Percent(name);
			metrics.put(name, percent);
			String propertyName = RATE_PROPERTYE_PREFIX + name;
			int rate = DEFAULT_RATE;
			try {
				rate = Integer.parseInt(System.getProperty(propertyName, String.valueOf(DEFAULT_RATE)));
			} catch (NumberFormatException e) {
			}
			timer.addListener(name, this, rate);
		}
		Object[] param = new Object[pairs.length];
		for (int i = 0; i < pairs.length; i++) {
			param[i] = pairs[i].getValue();
		}
		percent.putData(param);
	}

	@Override
	public void trriger(String clockName) {
		Percent percent = metrics.get(clockName);
		if (percent == null) {
			return;
		}
		try {
			if (way != null) way.push(percent.toStatisticSet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setWay(Way way) {
		this.way = way;
	}

}

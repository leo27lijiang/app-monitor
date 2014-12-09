package com.lefu.monitor.core.useful;

import java.util.Hashtable;
import java.util.Map;

import com.lefu.monitor.Pair;
import com.lefu.monitor.Useful;
import com.lefu.monitor.Way;
import com.lefu.monitor.core.RateTimer;
import com.lefu.monitor.core.Timer;
import com.lefu.monitor.core.complex.Throughputs;

/**
 * 吞吐量统计工具
 * @author jiang.li
 *
 */
public class ThroughputsUseful implements Useful, RateTimer {
	private final Map<String, Throughputs> metrics = new Hashtable<String, Throughputs>();
	private Way way;
	private Timer timer;
	
	public ThroughputsUseful(Way way) {
		this.way = way;
	}
	
	public ThroughputsUseful(Way way, Timer timer) {
		this(way);
		this.timer = timer;
	}
	
	@Override
	public void trriger(String clockName) {
		Throughputs throughputs = metrics.get(clockName);
		if (throughputs == null) {
			return;
		}
		try {
			if (way != null) way.push(throughputs.toStatisticSet()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void putData(String name, Pair... pairs) {
		Throughputs throughputs = metrics.get(name);
		if (throughputs == null) {
			throughputs = new Throughputs(name);
			metrics.put(name, throughputs);
			String propertyName = RATE_PROPERTYE_PREFIX + name;
			int rate = DEFAULT_RATE;
			try {
				rate = Integer.parseInt(System.getProperty(propertyName, String.valueOf(DEFAULT_RATE)));
			} catch (NumberFormatException e) {
			}
			timer.addListener(name, this, rate);
		}
		throughputs.put();
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setWay(Way way) {
		this.way = way;
	}

}

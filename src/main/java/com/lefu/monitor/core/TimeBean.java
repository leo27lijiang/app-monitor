package com.lefu.monitor.core;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeBean {
	private final AtomicInteger count;
	private final String name;
	private final RateTimer rateTimer;
	private int interval;
	
	public TimeBean(String name, RateTimer rateTime, int interval) {
		this.rateTimer = rateTime;
		this.interval = interval;
		this.count = new AtomicInteger(interval);
		this.name = name;
	}
	
	/**
	 * 检查指定间隔的时间是否达到，单位秒
	 * @return
	 */
	public RateTimer checkAndGet() {
		int c = count.getAndDecrement();
		if (c <= 1) {
			count.set(interval);
			return rateTimer;
		}
		return null;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		if (this.name == null) {
			return 0;
		}
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TimeBean)) {
			return false;
		}
		TimeBean target = (TimeBean) obj;
		if (target.getName() == null && this.name == null) {
			return true;
		}
		return target.getName().equals(name);
	}
	
}

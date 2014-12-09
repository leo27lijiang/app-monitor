package com.lefu.monitor.core;

import java.util.Set;

/**
 * 触发定时运行实体
 * @author jiang.li
 *
 */
public class Time implements Runnable {
	private final Set<TimeBean> timeBeans;
	
	public Time(Set<TimeBean> timeBeans) {
		this.timeBeans = timeBeans;
	}
	
	@Override
	public void run() {
		try {
			for (TimeBean b : timeBeans) {
				RateTimer rateTimer = b.checkAndGet();
				if (rateTimer != null) {
					try {
						rateTimer.trriger(b.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

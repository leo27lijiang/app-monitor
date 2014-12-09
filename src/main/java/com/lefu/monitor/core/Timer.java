package com.lefu.monitor.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.lefu.monitor.Resource;

/**
 * 定时器，每秒钟执行一次
 * @author jiang.li
 *
 */
public class Timer implements Resource {
	private final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new TimerThreadFactory());
	private final Set<TimeBean>  timeBeans;
	
	public Timer() {
		timeBeans = new HashSet<TimeBean>();
	}
	
	public void addListener(final String name, final RateTimer rateTimer, int interval) {
		timeBeans.add(new TimeBean(name, rateTimer, interval));
	}
	
	@Override
	public void startUp() {
		executor.scheduleWithFixedDelay(new Time(timeBeans), 5, 1, TimeUnit.SECONDS);
	}

	@Override
	public void destroy() {
		executor.shutdown();
	}
	
	public class TimerThreadFactory implements ThreadFactory {
		private final AtomicInteger counter = new AtomicInteger(0);
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setName("BasicTimer-" + counter.getAndIncrement());
			return t;
		}
		
	}
}

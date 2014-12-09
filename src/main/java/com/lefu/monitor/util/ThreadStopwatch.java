package com.lefu.monitor.util;

/**
 * 需要注意，应该每次在需要标记时间时使用 {@link ThreadStopwatch#mark()} 进行标记，而不是以构建实例的默认的标记行为。
 * <pre>
 * 在不使用 mark 的场景下：
 * 在一个流程中，如果第一次流程标记了时间，而没有进行获取，那么后续的流程获取是当前时间与第一次流程实例化此对象的时间间隔，在 {@link ThreadStopwatch#get()} 后重置
 * </pre> 
 * @author jiang.li
 *
 */
public class ThreadStopwatch implements Stopwatch {
	private static final ThreadLocal<Long> markTime = new ThreadLocal<Long>();
	
	public ThreadStopwatch() {
		Long time = markTime.get();
		if (time == null) {
			mark();
		}
	}
	
	@Override
	public void mark() {
		markTime.set(System.currentTimeMillis());
	}
	
	@Override
	public long get() {
		Long time = markTime.get();
		if (time == null) {
			return 0l;
		}
		long interval = System.currentTimeMillis() - time;
		markTime.set(null);
		return interval;
	}
	
}

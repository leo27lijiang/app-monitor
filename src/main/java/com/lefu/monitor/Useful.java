package com.lefu.monitor;

/**
 * 工具包定义
 * @author jiang.li
 *
 */
public interface Useful {
	/**
	 * 配置属性前缀
	 */
	public static final String RATE_PROPERTYE_PREFIX = "com.lefu.monitor.rate.";
	/**
	 * 指示工具是否支持单例模型
	 * @return
	 */
	public boolean isSingleton();
	/**
	 * 接收数据
	 * @param name
	 * @param pairs
	 */
	public void putData(String name, Pair... pairs);
}

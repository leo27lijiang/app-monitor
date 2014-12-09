package com.lefu.monitor;

/**
 * 接收器定义
 * @author jiang.li
 * 
 */
public interface Receiver {
	/**
	 * 发布一个空数据的指标
	 * @param name
	 */
	public void putNoneMetrics(String name);
	/**
	 * 发布一个key为null的指标数据
	 * @param name
	 * @param value
	 */
	public void putMetrics(String name, Object value);
	/**
	 * 发布一个指标数据
	 * @param name
	 * @param key
	 * @param value
	 */
	public void putMetrics(String name, String key, Object value);
	/**
	 * 发布一个指标数据
	 * @param name
	 * @param value
	 */
	public void putMetrics(String name, Pair value);
	/**
	 * 发布一个统计数据集合
	 * @param name
	 * @param values
	 */
	public void putStatisticSets(String name, Pair... values);
	/**
	 * 指标关联工具类
	 * @param metricName
	 * @param tool
	 */
	public void setUseful(String metricName, Useful tool);
}

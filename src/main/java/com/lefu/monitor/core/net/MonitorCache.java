package com.lefu.monitor.core.net;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.lefu.monitor.StatisticSet;
import com.lefu.monitor.core.format.JsonStatisticFormat;
import com.lefu.monitor.core.format.StatisticFormat;

/**
 * 监控数据缓冲区，每个监控指标只会存在一份数据，新的数据会覆盖旧的数据
 * @author jiang.li
 *
 */
public class MonitorCache {
	private StatisticFormat format = new JsonStatisticFormat();
	private Map<String, StatisticSet> monitorData = new Hashtable<String, StatisticSet>();
	
	/**
	 * 获取所有指标数据的字符串
	 * @return
	 */
	public String getContent() {
		List<StatisticSet> l = new ArrayList<StatisticSet>();
		for (StatisticSet s : monitorData.values()) {
			l.add(s);
		}
		return format.format(l);
	}
	
	/**
	 * 获取指定的指标数据
	 * @param metricsName
	 * @return
	 */
	public String getContent(String metricsName) {
		StatisticSet set = monitorData.get(metricsName);
		if (set == null) {
			return null;
		}
		return format.format(set);
	}
	
	/**
	 * 接收指标数据
	 * @param statistic
	 */
	public void putStatisticSet(StatisticSet statistic) {
		this.monitorData.put(statistic.getName(), statistic);
	}

	public void setFormat(StatisticFormat format) {
		if (format == null) {
			return;
		}
		this.format = format;
	}
	
}

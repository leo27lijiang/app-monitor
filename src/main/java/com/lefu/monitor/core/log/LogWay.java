package com.lefu.monitor.core.log;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lefu.monitor.StatisticSet;
import com.lefu.monitor.Way;
import com.lefu.monitor.core.format.JsonStatisticFormat;
import com.lefu.monitor.core.format.StatisticFormat;

/**
 * 日志通道
 * @author jiang.li
 *
 */
public class LogWay implements Way {
	private final Logger log;
	private StatisticFormat format = new JsonStatisticFormat();
	
	public LogWay(String logName) {
		this(LoggerFactory.getLogger(logName));
	}
	
	public LogWay(Logger log) {
		this.log = log;
	}
	
	@Override
	public void push(StatisticSet data) {
		log.info(format.format(data));
	}

	@Override
	public void push(List<StatisticSet> datas) {
		log.info(format.format(datas));
	}

	@Override
	public void setStatisticFormat(StatisticFormat format) {
		this.format = format;
	}

}

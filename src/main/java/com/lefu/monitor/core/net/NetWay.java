package com.lefu.monitor.core.net;

import io.netty.handler.logging.LogLevel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lefu.monitor.Resource;
import com.lefu.monitor.StatisticSet;
import com.lefu.monitor.Way;
import com.lefu.monitor.core.format.StatisticFormat;
import com.lefu.remote.netty.server.IOServer;

/**
 * 网络通道，通过监听端口的方式将监控内容输出至连接
 * @author jiang.li
 *
 */
public class NetWay implements Way, Resource {
	private static int DEFAULT_PORT = 15678;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	private IOServer server;
	private int port = DEFAULT_PORT;
	private boolean started;
	private final MonitorCache cache;
	
	static {
		try {
			DEFAULT_PORT = Integer.parseInt("com.lefu.monitor.netway.port", 15678);
		} catch (NumberFormatException e) {
		}
	}
	
	public NetWay() {
		cache = new MonitorCache();
	}
	
	@Override
	public void push(StatisticSet data) {
		if (data == null) {
			return;
		}
		cache.putStatisticSet(data);
	}

	@Override
	public void push(List<StatisticSet> datas) {
		for (StatisticSet set : datas) {
			push(set);
		}
	}

	@Override
	public void startUp() {
		if (started) {
			return;
		}
		server = new IOServer(new NetHandlerFactory(cache));
		try {
			server.setEnableIdleHandler(false);
			server.setEnableTimeoutHandler(false);
			server.setBothGroupSize(1, 1);
			server.setNettyLogLevel(LogLevel.WARN);
			server.bind(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		started = true;
		log.info("Monitor [NetWay] was started");
	}

	@Override
	public void destroy() {
		if (!started) {
			return;
		}
		if (server != null) {
			server.destroy();
		}
		started = false;
		log.info("Monitor [NetWay] was destroyed");
	}
	
	@Override
	public void setStatisticFormat(StatisticFormat format) {
		this.cache.setFormat(format);
	}

	public void setPort(int port) {
		this.port = port;
	}

}

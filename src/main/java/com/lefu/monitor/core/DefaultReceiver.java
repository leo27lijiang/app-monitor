package com.lefu.monitor.core;

import java.util.Hashtable;
import java.util.Map;

import com.lefu.monitor.Pair;
import com.lefu.monitor.Receiver;
import com.lefu.monitor.Useful;

public class DefaultReceiver implements Receiver {
	private Useful defaultTool;
	private Map<String, Useful> usefulTool = new Hashtable<String, Useful>();
	
	@Override
	public void putNoneMetrics(String name) {
		putStatisticSets(name);
	}
	
	@Override
	public void putMetrics(String name, Object value) {
		putMetrics(name, new Pair(value));
	}
	
	@Override
	public void putMetrics(String name, String key, Object value) {
		putMetrics(name, new Pair(key, value));
	}

	@Override
	public void putMetrics(String name, Pair value) {
		putStatisticSets(name, new Pair[]{value});
	}

	@Override
	public void putStatisticSets(String name, Pair... values) {
		Useful tool = usefulTool.get(name);
		if (tool == null) {
			tool = defaultTool;
		}
		if (tool != null) {
			try {
				tool.putData(name, values);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setUseful(String metricName, Useful tool) {
		usefulTool.put(metricName, tool);
	}

	public void setDefaultTool(Useful defaultTool) {
		this.defaultTool = defaultTool;
	}

	public void setUsefulTool(Map<String, Useful> usefulTool) {
		this.usefulTool.putAll(usefulTool);
	}
	
}

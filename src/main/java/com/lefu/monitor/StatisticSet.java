package com.lefu.monitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.lefu.monitor.util.DateUtil;

/**
 * 统计信息
 * @author jiang.li
 *
 */
public final class StatisticSet implements Serializable {
	private static final long serialVersionUID = -7128458116167763687L;
	private String name;
	private String timestamp;
	private Set<Pair> datapoints;
	
	public StatisticSet() {
		timestamp = DateUtil.dateFormat(System.currentTimeMillis());
		datapoints = new HashSet<Pair>();
	}
	
	public StatisticSet(String name, Pair... pairs) {
		this();
		this.name = name;
		if (pairs != null) {
			for (Pair p : pairs) {
				datapoints.add(p);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Pair> getDatapoints() {
		return datapoints;
	}
	
	public void setDatapoint(Pair pair) {
		datapoints.add(pair);
	}

	public void setDatapoints(Pair... pairs) {
		if (pairs != null) {
			for (Pair p : pairs) {
				datapoints.add(p);
			}
		}
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	@Override
	public int hashCode() {
		if (name == null) {
			return 0;
		}
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StatisticSet)) {
			return false;
		}
		StatisticSet target = (StatisticSet) obj;
		if (target.getName() == null && name == null) {
			return true;
		}
		return target.getName().equals(name);
	}
	
}

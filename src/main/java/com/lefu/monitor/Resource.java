package com.lefu.monitor;

/**
 * 监控实体定义
 * @author jiang.li
 *
 */
public interface Resource {
	/**
	 * 启动
	 */
	public void startUp();
	/**
	 * 销毁
	 */
	public void destroy();
	
}

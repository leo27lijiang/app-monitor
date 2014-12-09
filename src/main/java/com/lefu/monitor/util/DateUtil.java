package com.lefu.monitor.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具
 * @author jiang.li
 *
 */
public class DateUtil {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
	
	public static String dateFormat(Date date) {
		return formater.format(date);
	}
	
	public static String dateFormat(long timestamp) {
		return dateFormat(new Date(timestamp));
	}
	
}

package com.lefu.monitor;

/**
 * 指示不支持的指标数据类型
 * @author jiang.li
 *
 */
public class UnSupportedTypeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6699059506948018937L;
	
	public UnSupportedTypeException() {
		super();
	}
	
	public UnSupportedTypeException(String message) {
		super(message);
	}
	
	public UnSupportedTypeException(Throwable cause) {
		super(cause);
	}
	
}

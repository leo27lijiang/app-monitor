package com.lefu.monitor;

import java.io.Serializable;

/**
 * 键值队封装
 * @author jiang.li
 * 
 */
public final class Pair implements Serializable {
	private static final long serialVersionUID = 3799492407008714890L;
	private String key;
	private Object value;
	
	public Pair() {
		
	}
	
	public Pair(Object value) {
		this.value = value;
	}
	
	public Pair(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public void checkType() {
		if (this.value == null) {
			throw new NullPointerException();
		}
		if (this.value.getClass().isPrimitive() || this.value.getClass().isAssignableFrom(String.class)) {
		} else if (this.value instanceof Integer || this.value instanceof Long || this.value instanceof Short) {
		} else if (this.value instanceof Double || this.value instanceof Float || this.value instanceof Byte) {
		} else if (this.value instanceof Boolean || this.value instanceof Character) {
		} else {
			throw new UnSupportedTypeException(this.value.getClass().getName());
		}
	}
	
	@Override
	public int hashCode() {
		if (key == null) {
			return 0;
		}
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair target = (Pair) obj;
		if (target.getKey() == null && key == null) {
			return true;
		}
		return target.getKey().equals(key);
	}
	
}

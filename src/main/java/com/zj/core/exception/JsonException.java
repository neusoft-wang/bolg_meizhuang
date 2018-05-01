package com.zj.core.exception;

/**
 * 自定义异常，返回json格式数据
 */
public class JsonException extends Exception {

	private static final long serialVersionUID = 1340107282729826915L;

	public JsonException(String message) {
		super(message);
	}
	
}
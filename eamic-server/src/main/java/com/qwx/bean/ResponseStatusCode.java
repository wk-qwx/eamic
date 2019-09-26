package com.qwx.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseStatusCode {
	private final static Map<Integer, String> statusCode = new ConcurrentHashMap<Integer, String>();
	/**
	 * 完成
	 */
	public final static Integer C200 = 200;
	/**
	 * 重定向
	 */
	public final static Integer C300 = 300;
	/**
	 * 请求错误
	 */
	public final static Integer C400 = 400;
	/**
	 * 请求资源已被删除
	 */
	public final static Integer C404 = 404;
	/**
	 * 请求服务失败，请稍后再尝试
	 */
	public final static Integer C500 = 500;
	/**
	 * 服务不可用
	 */
	public final static Integer C501 = 501;
	/**
	 * 没有操作权限
	 */
	public final static Integer C502 = 502;
	static {
		statusCode.put(C200, "完成");
		statusCode.put(C300, "重定向");
		statusCode.put(C400, "请求错误");
		statusCode.put(C404, "请求资源已被删除");
		statusCode.put(C500, "请求服务失败，请稍后再尝试");
		statusCode.put(C501, "服务不可用");
		statusCode.put(C502, "没有操作权限");
	}

	public static String getMessage(Integer code) {
		return statusCode.get(code);
	}
}

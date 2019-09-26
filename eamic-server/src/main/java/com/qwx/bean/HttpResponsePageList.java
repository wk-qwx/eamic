package com.qwx.bean;

import java.io.Serializable;

public class HttpResponsePageList<T extends Serializable> {
	private int statusCode;
	private String message;
	private PageList<T> data;

	public HttpResponsePageList() {
		this(ResponseStatusCode.C200);
	}

	public HttpResponsePageList(int code) {
		this(code, ResponseStatusCode.getMessage(code));
	}

	public HttpResponsePageList(int code, String message) {
		this.statusCode = code;
		this.message = message;
	}

	public HttpResponsePageList(PageList<T> data) {
		this(ResponseStatusCode.C200, ResponseStatusCode.getMessage(ResponseStatusCode.C200), data);
	}
	public HttpResponsePageList(int code, String message, PageList<T> data) {
		this.statusCode = code;
		this.message = message;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageList<T> getData() {
		return data;
	}

	public void setData(PageList<T> pl) {
		this.data = pl;
	}
}

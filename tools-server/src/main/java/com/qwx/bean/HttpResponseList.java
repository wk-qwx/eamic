package com.qwx.bean;

import java.io.Serializable;
import java.util.List;

public class HttpResponseList<T extends Serializable> {
	private int statusCode;
	private String message;
	private List<T> data;

	public HttpResponseList() {
		this(ResponseStatusCode.C200);
	}

	public HttpResponseList(int code) {
		this(code, ResponseStatusCode.getMessage(code));
	}

	public HttpResponseList(int code, String message) {
		this.statusCode = code;
		this.message = message;
	}

	public HttpResponseList(List<T> data) {
		this(ResponseStatusCode.C200, ResponseStatusCode.getMessage(ResponseStatusCode.C200), data);
	}

	public HttpResponseList(int code, String message, List<T> data) {
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

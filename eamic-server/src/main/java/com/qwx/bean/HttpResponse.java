package com.qwx.bean;

import java.io.Serializable;

public class HttpResponse<T extends Serializable> {
	private int statusCode;
	private String message;
	private T data;

	public HttpResponse() {
		this(ResponseStatusCode.C200);
	}

	public HttpResponse(int code) {
		this(code, ResponseStatusCode.getMessage(code));
	}

	public HttpResponse(int code, String message) {
		this.statusCode = code;
		this.message = message;
	}

	public HttpResponse(T data) {
		this(ResponseStatusCode.C200, ResponseStatusCode.getMessage(ResponseStatusCode.C200), data);
	}

	public HttpResponse(int code, String message, T data) {
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

package com.qwx.bean;

import java.io.Serializable;

public class PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private int pageCount;

	public PageInfo() {
	}

	public PageInfo(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public PageInfo(int pageIndex, int pageSize, int totalCount) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public PageInfo(int pageIndex, int pageSize, int totalCount, int pageCount) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		int s = totalCount / pageSize;
		int y = totalCount % pageSize;
		this.pageCount = y > 0 ? s + 1 : s;
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}

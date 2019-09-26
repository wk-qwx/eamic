package com.qwx.bean;

import java.util.List;

public class PageList<T> {
	private PageInfo pageInfo;
	private List<T> dataSource;

	public PageList() {}

	public PageList(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageList(List<T> dataSource) {
		this.dataSource = dataSource;
	}

	public PageList(PageInfo pageInfo, List<T> dataSource) {
		this.pageInfo = pageInfo;
		this.dataSource = dataSource;
	}

	public List<T> getDataSource() {
		return dataSource;
	}

	public void setDataSource(List<T> dataSource) {
		this.dataSource = dataSource;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}

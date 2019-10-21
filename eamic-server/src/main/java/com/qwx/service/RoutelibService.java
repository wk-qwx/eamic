package com.qwx.service;

import com.qwx.bean.PageList;
import com.qwx.entity.RoutelibEntity;

/**
 * 线路信息接口
 * @author kal02
 *
 */
public interface RoutelibService {

	/**
	 * 获取电站分页列表
	 * @return 分页线路列表
	 */
	public PageList<RoutelibEntity> getStations(String jsonstr);
	
	public PageList<RoutelibEntity> getRouteByName(String jsonstr);
	
}

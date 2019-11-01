package com.qwx.service;

import java.util.List;

import com.qwx.entity.RoutelibEntity;

/**
 * 线路信息接口
 * @author kal02
 *
 */
public interface RoutelibService {

	/**
	 * 获取线路列表
	 * @param jsonstr 查询条件
	 * @param groupid 登录用户分组id
	 * @return 该用户的线路
	 */
	public List<RoutelibEntity> getRoutes(String jsonstr,String groupid);
	/**
	 * 根据线路名称模糊查询
	 * @param jsonstr 查询条件
	 * @param groupid 登录用户分组id
	 * @return 该用户下线路
	 */
	public List<RoutelibEntity> getRouteByName(String jsonstr,String groupid);
	/**
	 * 设备状态列表
	 * @param jsonstr 查询条件
	 * @param groupid 登录用户分组id
	 * @return 设备状态列表
	 *//*
	public String getRoutesOfState(String jsonstr,String groupid,String pageIndex,String pageSize);*/
	
}

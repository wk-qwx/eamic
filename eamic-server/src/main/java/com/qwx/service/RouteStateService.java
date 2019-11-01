package com.qwx.service;


/**
 * 线路状态视图接口
 * @author kal02
 *
 */
public interface RouteStateService {

	/**
	 * 设备状态列表
	 * @param jsonstr 查询条件
	 * @param groupid 登录用户分组id
	 * @return 设备状态列表
	 */
	public String getRoutesOfState(String jsonstr,String groupid,String pageIndex,String pageSize);
	
}

package com.qwx.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.RoutelibEntity;
import com.qwx.service.RoutelibService;

/**
 * 线路信息服务
 * @author kal02
 *
 */
@Service
public class RoutelibServiceImpl extends BaseService<RoutelibEntity> implements RoutelibService{


	public RoutelibServiceImpl() {
		tableName = "ea_routelib";
	}
	/**
	 * 获取分页列表
	 */
	public PageList<RoutelibEntity> getStations(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String pageindex = jsonobject.getString("pageIndex");
			String pagesize = jsonobject.getString("pageSize");		
			String station = jsonobject.getString("station");
			String stationname = jsonobject.getString("stationname");
			String sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"'";
			return getPageBySql(pageindex,pagesize,sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	
	/**
	 * 根据条件过滤
	 */
	public PageList<RoutelibEntity> getRouteByName(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String stationname = jsonobject.getString("stationname");	
			String station = jsonobject.getString("station");
			String routename = jsonobject.getString("routename");
			String sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' routename = '%"+routename+"%'";
			
			return getPageBySql("1","15",sql);			
		}catch(Exception e){
		
		}
		return null;
	}
}

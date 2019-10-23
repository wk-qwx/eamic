package com.qwx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public String getStations(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String pageindex = jsonobject.getString("pageIndex");
			String pagesize = jsonobject.getString("pageSize");		
			String station = jsonobject.getString("station");
			String stationname = jsonobject.getString("stationname");
			String sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"'";
			return sumScore(getPageBySql(pageindex,pagesize,sql));
			
		}catch(Exception e){
		
		}
		return "null";
	}
	
	/**
	 * 根据条件过滤
	 */
	public String getRouteByName(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String stationname = jsonobject.getString("stationname");	
			String station = jsonobject.getString("station");
			String routename = jsonobject.getString("routename");
			String sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' and routename like '%"+routename+"%'";
			
			return sumScore(getPageBySql("1","15",sql));			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 计算总扣分
	 * @param list
	 * @return
	 */
	public String sumScore(PageList<RoutelibEntity> list){
		List<RoutelibEntity> entitys = list.getDataSource();
		HashMap<String, Object> routeMap = new HashMap<String, Object>();
		List<String> routelist = new ArrayList<String>();
		int score=0;//该线路扣分总数
		for(int i = 0; i < entitys.size(); i++){
			String sql2 = "select score from ea_defect where routeid = '"+entitys.get(i).getId()+"'";
			List<String> scorelist = getFieldBySql(sql2);
			for(int j = 0; j<scorelist.size(); j++){
				
				score = Integer.parseInt(scorelist.get(j));
				score+=score;
			}
			routeMap.put("id", entitys.get(i).getId());
			routeMap.put("station", entitys.get(i).getStation());
			routeMap.put("stationname", entitys.get(i).getStationname());
			routeMap.put("routename", entitys.get(i).getRoutename());
			routeMap.put("groupname", entitys.get(i).getGroupname());
			routeMap.put("groupid", entitys.get(i).getGroupid());
			routeMap.put("score", score);
			JSONObject jsonObj = new JSONObject(routeMap);
			routelist.add(i, jsonObj.toString());
		}
		return routelist.toString();
		
	}
}

package com.qwx.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
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
	 * 获取线路列表
	 */
	public List<RoutelibEntity> getRoutes(String jsonstr,String groupid) {
		try{
			String sql = "";
			if(jsonstr.equals("null")){
				if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))
					sql = "select * from ea_routelib where groupid = '"+groupid+"'";
				else
					sql = "select * from ea_routelib";							
			}else{
				JSONObject jsonobject = JSONObject.parseObject(jsonstr);		
				String station = jsonobject.getString("station");
				String stationname = jsonobject.getString("stationname");
				
				if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' and groupid = '"+groupid+"'";
				else					
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"'";
			}
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	
	/**
	 * 根据线路名称模糊查询
	 */
	public List<RoutelibEntity> getRouteByName(String jsonstr,String groupid) {
		try{
			String sql = "";
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String stationname = jsonobject.getString("stationname");	
			String station = jsonobject.getString("station");
			String routename = jsonobject.getString("routename");
			if(routename != null){
				if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))					
					sql = "select * from ea_routelib where groupid = '"+groupid+"' and routename like '%"+routename+"%'";
				else
					sql = "select * from ea_routelib where routename like '%"+routename+"%'";
			}if(routename != null && station != null&&stationname != null){
				if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))
					sql = "select * from ea_routelib where groupid = '"+groupid+"' and station = '"+station+"' and stationname = '"+stationname+"' and routename like '%"+routename+"%'";					
				else
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' and routename like '%"+routename+"%'";
			}
			return getBySql(sql);			
		}catch(Exception e){
		
		}
		return null;
	}	
	
}

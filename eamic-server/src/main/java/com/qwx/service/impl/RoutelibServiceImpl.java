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
				if(groupid.equals("0"))sql = "select * from ea_routelib";
				else sql = "select * from ea_routelib where groupid = '"+groupid+"'";			
			}else{
				JSONObject jsonobject = JSONObject.parseObject(jsonstr);		
				String station = jsonobject.getString("station");
				String stationname = jsonobject.getString("stationname");
				
				if(groupid.equals("0")){
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"'";
				}else{
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' and groupid = '"+groupid+"'";
				}
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
				if(groupid.equals("0"))
					sql = "select * from ea_routelib where routename like '%"+routename+"%'";
				else
					sql = "select * from ea_routelib where groupid = '"+groupid+"' and routename like '%"+routename+"%'";
			}if(routename != null && station != null&&stationname != null){
				if(groupid.equals("0"))
					sql = "select * from ea_routelib where station = '"+station+"' and stationname = '"+stationname+"' and routename like '%"+routename+"%'";
				else
					sql = "select * from ea_routelib where groupid = '"+groupid+"' and station = '"+station+"' and stationname = '"+stationname+"' and routename like '%"+routename+"%'";
			}
			return getBySql(sql);			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 查询线路状态列表
	 *//*
	public String getRoutesOfState(String jsonstr,String groupid,String pageIndex,String pageSize){
		String sql = "select a.id,a.routename,a.groupid,case when b.score is null then '100' when flag = '1' then '100' else MIN(b.score) end score,"
				+ "case when b.defectplace is null then '无' when flag = '1' then '无' else b.defectplace end defectplace,"
				+ "case when b.devicestatus is null then '正常状态' when flag = '1' then '正常状态' else b.devicestatus end devicestatus,b.flag"
				+ " from ea_routelib a LEFT JOIN ea_defect b on a.id = b.routeid GROUP BY a.id";
		String where = "";
		JSONObject jsonobject = JSONObject.parseObject(jsonstr);
		String devicestatus = jsonobject.getString("devicestatus");	
		String routename = jsonobject.getString("routename");
		if(!devicestatus.equals("") && devicestatus !=null){
			if(groupid.equals("0"))
				where = " having flag = '0' devicestatus = '"+devicestatus+"' LIMIT "+pageIndex+","+pageSize+"";
			else
				where = " having groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' LIMIT "+pageIndex+","+pageSize+"";
		}if(devicestatus !=null && routename !=null){
			if(groupid.equals("0"))
				//sql = "select a.routename,a.station,a.stationname,b.* from ea_routelib a LEFT JOIN ea_defect b on a.id = b.routeid where b.devicestatus = '"+devicestatus+"' and ";
				where = " having devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' LIMIT "+pageIndex+","+pageSize+"";
			else
				where = " having groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' LIMIT "+pageIndex+","+pageSize+"";
		}
		
		try {
			//返回分页列表
			List<Object> stateList = getObjectBySql(sql+where,RouteStateEntity.class);
			PageInfo pi = getPageInfo(pageIndex, pageSize);
			if (pi == null) return null;
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery("select count(*) from (" + sql+where + ") t");
			pi.setTotalCount(Integer.valueOf(query.getSingleResult().toString()));
			
			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put("DataSource", JSONObject.toJSONString(stateList));
			resultMap.put("PageInfo", JSONObject.toJSONString(pi));
			return resultMap.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}*/
	
}

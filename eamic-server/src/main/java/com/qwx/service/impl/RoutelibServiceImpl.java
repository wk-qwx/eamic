package com.qwx.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.bean.PageInfo;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.entity.RouteStateEntity;
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
	 */
	public String getRoutesOfState(String jsonstr,String groupid,String pageIndex,String pageSize){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.routename,a.groupid,case when b.score is null then '100' else MIN(b.score) end score,"
				+ "case when b.defectplace is null then '无' else b.defectplace end defectplace,"
				+ "case when b.devicestatus is null then '正常状态' else b.devicestatus end devicestatus"
				+ " from ea_routelib a LEFT JOIN ea_defect b on a.id = b.routeid GROUP BY a.id");
		JSONObject jsonobject = JSONObject.parseObject(jsonstr);
		String devicestatus = jsonobject.getString("devicestatus");	
		String routename = jsonobject.getString("routename");
		if(!devicestatus.equals("") && devicestatus !=null){
			if(groupid.equals("0"))
				sql.append(" having devicestatus = '"+devicestatus+"' LIMIT "+pageIndex+","+pageSize+"");
			else
				sql.append(" having groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' LIMIT "+pageIndex+","+pageSize+"");
		}if(devicestatus !=null && routename !=null){
			if(groupid.equals("0"))
				//sql = "select a.routename,a.station,a.stationname,b.* from ea_routelib a LEFT JOIN ea_defect b on a.id = b.routeid where b.devicestatus = '"+devicestatus+"' and ";
				sql.append(" having devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' LIMIT "+pageIndex+","+pageSize+"");
			else
				sql.append(" having groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' LIMIT "+pageIndex+","+pageSize+"");
		}
		
		try {
			//返回分页列表
			//PageList<RoutelibEntity> pagelist = getPageBySql(pageIndex,pageSize,sql.toString());
			List<Object> stateList = getObjectBySql(sql.toString(),RouteStateEntity.class);
			PageInfo pi = getPageInfo(pageIndex, pageSize);
			if (pi == null) return null;
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery("select count(*) from (" + sql.toString() + ") t");
			pi.setTotalCount(Integer.valueOf(query.getSingleResult().toString()));
			
			return JSONObject.toJSONString(stateList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 计算总扣分
	 * @param list
	 * @return
	 */
	/*public String sumScore(List<RoutelibEntity> list){
		HashMap<String, Object> routeMap = new HashMap<String, Object>();
		List<String> routelist = new ArrayList<String>();
		int deduct=0;//该线路扣分总数
		for(int i = 0; i < list.size(); i++){
			String sql2 = "select deduct from ea_defect where routeid = '"+list.get(i).getId()+"' and flag='0'";
			List<String> deductlist = getFieldBySql(sql2);
			for(int j = 0; j<deductlist.size(); j++){
				
				deduct = Integer.parseInt(deductlist.get(j));
				deduct+=deduct;
			}
			routeMap.put("id", list.get(i).getId());
			routeMap.put("station", list.get(i).getStation());
			routeMap.put("stationname", list.get(i).getStationname());
			routeMap.put("routename", list.get(i).getRoutename());
			routeMap.put("groupname", list.get(i).getGroupname());
			routeMap.put("groupid", list.get(i).getGroupid());
			routeMap.put("deduct", deduct);
			JSONObject jsonObj = new JSONObject(routeMap);
			routelist.add(i, jsonObj.toString());
		}
		return routelist.toString();
		
	}*/
}

package com.qwx.service.impl;


import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.RouteStateEntity;
import com.qwx.service.RouteStateService;

/**
 * 线路信息服务
 * @author kal02
 *
 */
@Service
public class RouteStateServiceImpl extends BaseService<RouteStateEntity> implements RouteStateService{


	public RouteStateServiceImpl() {
		tableName = "ea_routestate_v";
	}
	
	/**
	 * 查询线路状态列表
	 */
	public String getRoutesOfState(String jsonstr,String groupid,String pageIndex,String pageSize){
		String sql = "select * from ea_routestate_v";
		String where = "";
		JSONObject jsonobject = JSONObject.parseObject(jsonstr);
		String devicestatus = jsonobject.getString("devicestatus");	
		String routename = jsonobject.getString("routename");
		if(!devicestatus.equals("") && devicestatus !=null){
			if(groupid.equals("0"))
				where = " where devicestatus = '"+devicestatus+"' ORDER BY cast(score as SIGNED)";
			else
				where = " where groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' ORDER BY cast(score as SIGNED)";
		}if(devicestatus !=null && routename !=null){
			if(groupid.equals("0"))
				//sql = "select a.routename,a.station,a.stationname,b.* from ea_routelib a LEFT JOIN ea_defect b on a.id = b.routeid where b.devicestatus = '"+devicestatus+"' and ";
				where = " where devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' ORDER BY cast(score as SIGNED)";
			else
				where = " where groupid='"+groupid+"' and devicestatus = '"+devicestatus+"' and routename like '%"+routename+"%' ORDER BY cast(score as SIGNED)";
		}
		
		try {
			//返回分页列表
			PageList<RouteStateEntity> pagelist = getPageBySql(pageIndex,pageSize,sql+where);
			return JSONObject.toJSONString(pagelist,SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}

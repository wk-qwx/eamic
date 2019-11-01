package com.qwx.service.impl;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectLibEntity;
import com.qwx.service.DefectLibService;

/**
 * 缺陷信息服务
 * @author kal02
 *
 */
@Service
public class DefectLibServiceImpl extends BaseService<DefectLibEntity> implements DefectLibService{

	public DefectLibServiceImpl() {
		tableName = "ea_defectlib_v";
	}

	/**
	 * 缺陷信息筛选查询分页列表
	 */
	public String getDefectsByFilter(String pageIndex, String pageSize, String groupid, String where){
		String sql = "";
		if(where.equals("null")){
			if(groupid.equals("0")){
				sql = "select * from ea_defectlib_v ORDER BY finddate desc";
			}else{				
				sql = "select * from ea_defectlib_v where groupid ='"+groupid+"' ORDER BY finddate desc";
			}
		}else{
			JSONObject jsonobject = JSONObject.parseObject(where);
			if(groupid.equals("0")){
				sql = "select * from ea_defectlib_v where "+jsonobject.getString("where") + " ORDER BY finddate desc";
			}else{				
				sql = "select * from ea_defectlib_v where groupid ='"+groupid+"' and "+jsonobject.getString("where") + " ORDER BY finddate desc";
			}
		}
		try{
			//返回分页列表
			PageList<DefectLibEntity> pagelist = getPageBySql(pageIndex,pageSize,sql);
			return JSONObject.toJSONString(pagelist,SerializerFeature.WriteMapNullValue);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}		
	}
	
}

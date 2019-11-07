package com.qwx.service.impl;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qwx.database.BaseService;
import com.qwx.entity.DeductRuleEntity;
import com.qwx.service.DeductRuleService;

/**
 * 扣分导则服务
 * @author kal02
 *
 */
@Service
public class DeductRuleServiceImpl extends BaseService<DeductRuleEntity> implements DeductRuleService{


	public DeductRuleServiceImpl() {
		tableName = "ea_deduct_rule";
	}
	
	/**
	 * 扣分导则
	 */
	public String Guideline(String jsonstr) {
		try{
			StringBuffer sqlstr = new StringBuffer();
			String filed = "*";			
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);			
			if(jsonobject==null)return null;
			
			Iterator<String> it =  jsonobject.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next();
				sqlstr.append(" "+key+"='"+ jsonobject.getString(key)+"' and");				
				
	        }
			if(jsonobject.getString("defectlevel") != null && jsonobject.getString("defectlevel") != "")filed = "*";
			else if(jsonobject.getString("defecttype") != null && jsonobject.getString("defecttype") != "")filed = "defectlevel";
			else if(jsonobject.getString("defectplace") != null && jsonobject.getString("defectplace") != "")filed = "defecttype";
			else if(jsonobject.getString("unit") != null && jsonobject.getString("unit") != "")filed = "defectplace";
			else if(jsonobject.getString("devicetype") != null && jsonobject.getString("devicetype") != "")filed = "unit";
			
			String sql = "select distinct "+filed+" from ea_deduct_rule where ";
				
			if(filed.equals("*")){
				sql = "select "+filed+" from ea_deduct_rule where ";
				sql += sqlstr.toString();
				sql = sql.substring(0, sql.length()-4);	
				return JSONObject.toJSONString(getBySql(sql));
			}else{
				sql += sqlstr.toString();
				sql = sql.substring(0, sql.length()-4);
			}
							
			return JSONObject.toJSONString(getFieldBySql(sql));
			
		}catch(Exception e){
		
		}
		return null;
	}
}

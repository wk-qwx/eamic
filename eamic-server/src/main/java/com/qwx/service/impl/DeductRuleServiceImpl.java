package com.qwx.service.impl;

import java.util.Iterator;
import java.util.List;

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
	public List<DeductRuleEntity> Guideline(String jsonstr) {
		try{
			StringBuffer sqlstr = new StringBuffer();
			String sql = "select * from ea_deduct_rule where ";
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);			
			if(jsonobject==null)return null;
			
			Iterator<String> it =  jsonobject.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next();
				sqlstr.append(" "+key+"='"+ jsonobject.getString(key)+"' and");
	        }
			sql += sqlstr.toString();
			sql = sql.substring(0, sql.length()-4);						
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
}

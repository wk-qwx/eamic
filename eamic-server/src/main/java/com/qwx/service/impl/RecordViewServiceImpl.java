package com.qwx.service.impl;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.RecordViewEntity;
import com.qwx.service.RecordViewService;
/**
 * 缺陷信息服务
 * @author kal02
 *
 */
@Service
public class RecordViewServiceImpl extends BaseService<RecordViewEntity> implements RecordViewService{

	public RecordViewServiceImpl() {
		tableName = "ea_record_v";
	}
	/**
	 * 操作记录筛选查询分页列表
	 */
	public PageList<RecordViewEntity> getRecordByFilter(String pageIndex, String pageSize, String whereStr){
		String sql = "";
		if(whereStr.equals("null")){
			sql = "select * from ea_record_v ORDER BY opertime desc";			
		}else{			
			JSONObject jsonobject = JSONObject.parseObject(whereStr);
			sql = "select * from ea_record_v where "+jsonobject.getString("where") + " ORDER BY opertime desc";
		}
		try{
			//返回分页列表
			return getPageBySql(pageIndex,pageSize,sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
}

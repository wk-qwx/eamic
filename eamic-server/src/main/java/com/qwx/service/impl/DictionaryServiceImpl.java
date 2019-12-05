package com.qwx.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.qwx.database.BaseService;
import com.qwx.entity.DictionaryEntity;
import com.qwx.service.DictionaryService;

/**
 * 字典服务
 * @author kal02
 *
 */
@Service
public class DictionaryServiceImpl extends BaseService<DictionaryEntity> implements DictionaryService{


	public DictionaryServiceImpl() {
		tableName = "dictionary";
	}
	
	/**
	 * 获取字典列表
	 */
	public List<DictionaryEntity> getDictionary(String jsonstr) {
		try{
			String sql = "";
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String name = jsonobject.getString("name");
			String pid = jsonobject.getString("pid");
			if(name==null||name.equals("")){
				return null;
			}else{
				sql = "select * from dictionary where name = '"+name+"'";
			}
			if(pid!=null && !pid.equals(""))sql = "select * from dictionary where name = '"+name+"' and pid = '"+pid+"'";
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 字典模糊查询
	 */
	public List<DictionaryEntity> getStationByName(String jsonstr) {
		try{
			String sql = "";
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String name = jsonobject.getString("name");
			String display = jsonobject.getString("display");
			if(name==null){
				return null;
			}else{
				sql = "select * from dictionary where name = '"+name+"' and display like '%"+display+"%'";
			}
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 获取字典列表By Code
	 */
	public List<DictionaryEntity> getDicByCode(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String name = jsonobject.getString("name");
			String code = jsonobject.getString("code");
			StringBuffer str = new StringBuffer();
			String[] arr = code.split(",");
			for(int i = 0; i < arr.length; i++){
				if(i==arr.length-1){
					str.append("'"+arr[i]+"'");
				}else{
					str.append("'"+arr[i]+"',");	
				}
			}
			String sql = "select * from dictionary where name = '"+name+"' and code in("+str.toString()+")";
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}	
}

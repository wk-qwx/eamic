package com.qwx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.database.BaseService;
import com.qwx.entity.Dictionary2Entity;
import com.qwx.service.DictionaryService;
import com.qwx.util.Des3Util;
import com.qwx.util.DictionaryTreeEntity;

/**
 * 字典服务
 * @author kal02
 *
 */
@Service
public class DictionaryServiceImpl extends BaseService<Dictionary2Entity> implements DictionaryService{


	public DictionaryServiceImpl() {
		tableName = "dictionary";
	}
	
	/**
	 * 获取字典列表
	 */
	public List<Dictionary2Entity> getDictionary(String jsonstr) {
		try{
			
			String sql = "";
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String name = jsonobject.getString("name");
			String pid = jsonobject.getString("pid");
			if(name==null||name.equals("")){
				return null;
			}else{
				//name = Des3Util.decode(name);
				sql = "select * from dictionary where level = '1' and name = '"+name+"'";
			}
			if(pid!=null && !pid.equals("")){		
				//pid = Des3Util.decode(pid);
				sql = "select * from dictionary where level = '2' and name = '"+name+"' and pid = '"+pid+"'";
			}
			return getBySql(sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 分类加载树形
	 */
	public List<DictionaryTreeEntity> getClassTree() {
		try{
			String sql = "select * from dictionary where name = '国网衡阳供电公司' and level='1'";
			List<Dictionary2Entity> list = getBySql(sql);			
			List<DictionaryTreeEntity> treeEntitys = new ArrayList<DictionaryTreeEntity>();
			for(Dictionary2Entity Entity : list){
				DictionaryTreeEntity treeEntity = new DictionaryTreeEntity();
				String sql2 = "select * from dictionary where name = '国网衡阳供电公司' and level='2' and pid = '"+Entity.getCode()+"'";	
				treeEntity.setId(Entity.getId());
				treeEntity.setDisplay(Entity.getDisplay());
				treeEntity.setCode(Entity.getCode());
				treeEntity.setChildren(getBySql(sql2));
				treeEntitys.add(treeEntity);
			}
			return treeEntitys;
		}catch(Exception e){
		
		}
		return null;
	}
	
}

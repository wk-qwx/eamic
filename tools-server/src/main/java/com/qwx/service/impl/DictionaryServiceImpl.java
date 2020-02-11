package com.qwx.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.Dictionary2Entity;
import com.qwx.service.DictionaryService;
import com.qwx.util.DictionaryTreeEntity2;

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
	 * 初始化字典列表
	 */
	public PageList<Dictionary2Entity> dicReload(String whereStr, String page, String limit) {
		try{	
			String sql="";
			if(whereStr==null||whereStr.equals("")){
				return null;
			}else{
				//name = Des3Util.decode(name);
				sql = "select * from dictionary where "+whereStr+"";
			}
			return getPageBySql(page, limit, sql);
			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 分类加载树形
	 */
	public List<DictionaryTreeEntity2> getClassTree(String name) {
		try{
			String sql="";
						
			List<DictionaryTreeEntity2> treeEntitys = new ArrayList<DictionaryTreeEntity2>();
			if(!name.equals("") && name != null){				
				sql = "select * from dictionary where name = '"+name+"' and level='1'";
				List<Dictionary2Entity> list = getBySql(sql);
				for(Dictionary2Entity Entity : list){
					DictionaryTreeEntity2 treeEntity = new DictionaryTreeEntity2();
					sql = "select * from dictionary where name = '"+name+"' and level='2' and pid = '"+Entity.getCode()+"'";	
					treeEntity.setId(Entity.getId());
					treeEntity.setDisplay(Entity.getDisplay());
					treeEntity.setCode(Entity.getCode());
					treeEntity.setTitle(Entity.getDisplay());
					List<Dictionary2Entity> list2 = getBySql(sql);
					List<DictionaryTreeEntity2> treeEntitys2 = new ArrayList<DictionaryTreeEntity2>();
					for(Dictionary2Entity entity2 : list2){
						DictionaryTreeEntity2 treeEntity2 = new DictionaryTreeEntity2();						
						treeEntity2.setId(entity2.getId());
						treeEntity2.setName(entity2.getName());
						treeEntity2.setLevel(entity2.getLevel());
						treeEntity2.setDisplay(entity2.getDisplay());
						treeEntity2.setCode(entity2.getCode());
						treeEntity2.setTitle(entity2.getDisplay());		
						treeEntitys2.add(treeEntity2);
					}
					treeEntity.setChildren(treeEntitys2);
					treeEntitys.add(treeEntity);
				}
				return treeEntitys;
			}	
			
		}catch(Exception e){
		
		}
		return null;
	}
	public List<DictionaryTreeEntity2> loadTree() {
		try{
			String sql="select * from dictionary where level='0'";
			List<Dictionary2Entity> list = getBySql(sql);			
			List<DictionaryTreeEntity2> treeEntitys = new ArrayList<DictionaryTreeEntity2>();
			for(Dictionary2Entity entity : list){	
				DictionaryTreeEntity2 treeEntity = new DictionaryTreeEntity2();						
				treeEntity.setId(entity.getId());
				treeEntity.setName(entity.getName());
				treeEntity.setLevel(entity.getLevel());
				treeEntity.setDisplay(entity.getDisplay());
				treeEntity.setCode(entity.getCode());
				treeEntity.setTitle(entity.getDisplay());
				sql = "select * from dictionary where name = '"+entity.getName()+"' and level='1' and pid = '"+entity.getCode()+"'";
				List<Dictionary2Entity> list2 = getBySql(sql);
				List<DictionaryTreeEntity2> treeEntitys2 = new ArrayList<DictionaryTreeEntity2>();
				for(Dictionary2Entity entity2 : list2){
					DictionaryTreeEntity2 treeEntity2 = new DictionaryTreeEntity2();						
					treeEntity2.setId(entity2.getId());
					treeEntity2.setName(entity2.getName());
					treeEntity2.setLevel(entity2.getLevel());
					treeEntity2.setDisplay(entity2.getDisplay());
					treeEntity2.setCode(entity2.getCode());
					treeEntity2.setTitle(entity2.getDisplay());	
					sql = "select * from dictionary where name = '"+entity.getName()+"' and level='2' and pid = '"+entity2.getCode()+"'";
					List<Dictionary2Entity> list3 = getBySql(sql);
					if(list3.size()==0){
						treeEntitys2.add(treeEntity2);
						continue;
					}
					List<DictionaryTreeEntity2> treeEntitys3 = new ArrayList<DictionaryTreeEntity2>();
					for(Dictionary2Entity entity3 : list3){
						DictionaryTreeEntity2 treeEntity3 = new DictionaryTreeEntity2();						
						treeEntity3.setId(entity3.getId());
						treeEntity3.setName(entity3.getName());
						treeEntity3.setLevel(entity3.getLevel());
						treeEntity3.setDisplay(entity3.getDisplay());
						treeEntity3.setCode(entity3.getCode());
						treeEntity3.setTitle(entity3.getDisplay());		
						treeEntitys3.add(treeEntity3);
						
					}
					treeEntity2.setChildren(treeEntitys3);
					treeEntitys2.add(treeEntity2);
					
				}
				treeEntity.setChildren(treeEntitys2);
				treeEntitys.add(treeEntity);
					
			}
			return treeEntitys;
		}catch(Exception e){
		
		}
		return null;
	}
}

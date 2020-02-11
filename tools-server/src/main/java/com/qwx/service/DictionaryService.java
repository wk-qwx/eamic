package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.Dictionary2Entity;
import com.qwx.util.DictionaryTreeEntity2;

/**
 * 字典信息接口
 * @author kal02
 *
 */
public interface DictionaryService {
	/**
	 * 通过字典名称读取字典值
	 * @param jsonstr
	 * @return
	 */
	public List<Dictionary2Entity> getDictionary(String jsonstr);
	
	/**
	 * 初始化字典列表
	 * @param jsonstr
	 * @return
	 */
	public PageList<Dictionary2Entity> dicReload(String jsonstr, String page, String limit);
	/**
	 * 分类栏目
	 * @param name
	 * @return
	 */
	public List<DictionaryTreeEntity2> getClassTree(String name);
	/**
	 * 加载字典树
	 * @param name
	 * @return
	 */
	public List<DictionaryTreeEntity2> loadTree();
}

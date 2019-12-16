package com.qwx.service;

import java.util.List;

import com.qwx.entity.Dictionary2Entity;
import com.qwx.util.DictionaryTreeEntity;

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
	public List<DictionaryTreeEntity> getClassTree();
}

package com.qwx.service;

import java.util.List;

import com.qwx.entity.DictionaryEntity;

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
	public List<DictionaryEntity> getDictionary(String jsonstr);
	public List<DictionaryEntity> getStationByName(String jsonstr);
	public List<DictionaryEntity> getDicByCode(String jsonstr);
}

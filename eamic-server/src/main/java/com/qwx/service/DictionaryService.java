package com.qwx.service;

import java.util.List;

import com.qwx.entity.DictionaryEntity;

/**
 * 字典信息接口
 * @author kal02
 *
 */
public interface DictionaryService {

	public List<DictionaryEntity> getDictionary(String jsonstr);
	public List<DictionaryEntity> getDicByCode(String jsonstr);
}

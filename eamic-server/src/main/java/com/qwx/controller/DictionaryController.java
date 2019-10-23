package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponseList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.DictionaryEntity;
import com.qwx.service.DictionaryService;

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseController<DictionaryEntity> {

	
	public DictionaryController() {
		tableName = "dictionary";
	}

	@Resource
	DictionaryService dictionaryService;
	/**
	 * 根据字典名称读取字典列表
	 */
	@RequestMapping(value = "/getDictionary", method = RequestMethod.POST)
	public HttpResponseList<DictionaryEntity> getDictionary(@RequestBody String jsonstr) {
		try {			
			return new HttpResponseList<DictionaryEntity>(dictionaryService.getDictionary(jsonstr));
		} catch (Exception e) {
			return new HttpResponseList<DictionaryEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 字典模糊查询
	 */
	@RequestMapping(value = "/getStationByName", method = RequestMethod.POST)
	public HttpResponseList<DictionaryEntity> getStationByName(@RequestBody String jsonstr) {
		try {			
			return new HttpResponseList<DictionaryEntity>(dictionaryService.getStationByName(jsonstr));
		} catch (Exception e) {
			return new HttpResponseList<DictionaryEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 读取字典列表 By code
	 */
	@RequestMapping(value = "/getDicByCode", method = RequestMethod.POST)
	public HttpResponseList<DictionaryEntity> getDicByCode(@RequestBody String jsonstr) {
		try {		
			//dictionaryService.test();
			return new HttpResponseList<DictionaryEntity>(dictionaryService.getDicByCode(jsonstr));
		} catch (Exception e) {
			return new HttpResponseList<DictionaryEntity>(ResponseStatusCode.C400);
		}
	}
}

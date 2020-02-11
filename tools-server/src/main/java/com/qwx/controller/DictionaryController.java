package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponseList;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.Dictionary2Entity;
import com.qwx.service.DictionaryService;
import com.qwx.util.DictionaryTreeEntity2;

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseController<Dictionary2Entity> {

	
	public DictionaryController() {
		tableName = "dictionary";
	}

	@Resource
	DictionaryService dictionaryService;
	/**
	 * 根据字典名称读取字典列表
	 */
	@RequestMapping(value = "/getDictionary", method = RequestMethod.POST)
	public HttpResponseList<Dictionary2Entity> getDictionary(@RequestBody String jsonstr) {
		try {			
			return new HttpResponseList<Dictionary2Entity>(dictionaryService.getDictionary(jsonstr));
		} catch (Exception e) {
			return new HttpResponseList<Dictionary2Entity>(ResponseStatusCode.C400);
		}
	}
	
	/**
	 * 字典列表初始化
	 */
	@RequestMapping(value = "/dicReload", method = RequestMethod.GET)
	public HttpResponsePageList<Dictionary2Entity> dicReload(@RequestParam("whereStr") String whereStr, @RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			return new HttpResponsePageList<Dictionary2Entity>(dictionaryService.dicReload(whereStr, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<Dictionary2Entity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 分类加载树形
	 */
	@RequestMapping(value = "/getClassTree", method = RequestMethod.GET)
	public HttpResponseList<DictionaryTreeEntity2> getClassTree(@RequestParam("name") String name) {
		try {			
			return new HttpResponseList<DictionaryTreeEntity2>(dictionaryService.getClassTree(name));
		} catch (Exception e) {
			return new HttpResponseList<DictionaryTreeEntity2>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 分类加载树形
	 */
	@RequestMapping(value = "/loadTree", method = RequestMethod.GET)
	public HttpResponseList<DictionaryTreeEntity2> loadTree() {
		try {			
			return new HttpResponseList<DictionaryTreeEntity2>(dictionaryService.loadTree());
		} catch (Exception e) {
			return new HttpResponseList<DictionaryTreeEntity2>(ResponseStatusCode.C400);
		}
	}
	
}

package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponseList;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.service.ToolsLibService;


@RestController
@RequestMapping(value = "/toolslib")
public class ToolsLibController extends BaseController<ToolsLib2Entity> {

	
	public ToolsLibController() {
		tableName = "tl_toolslib";
	}

	@Resource
	ToolsLibService toolslibService;
	/**
	 * 安全工器具更新
	 */
	@RequestMapping(value = "/updatex", method = RequestMethod.POST)
	public HttpResponse<String> updatex(@RequestBody ToolsLib2Entity entity) {
		try {			
			
			return new HttpResponse<String>(toolslibService.updatex(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
	
	
}

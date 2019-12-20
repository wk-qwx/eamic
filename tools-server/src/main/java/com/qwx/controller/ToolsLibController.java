package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.bean.HttpResponseList;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.QrcodeView2Entity;
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
	 * 获取工器具列表
	 */
	@RequestMapping(value = "/getToolList", method = RequestMethod.GET)
	public HttpResponsePageList<ToolsLib2Entity> getToolList(@RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLib2Entity>(toolslibService.getToolList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLib2Entity>(ResponseStatusCode.C400);
		}
	}
	
	/**
	 * 获取安全工器具信息
	 */
	@RequestMapping(value = "/getToolInfo/{qrcode}", method = RequestMethod.POST)
	public HttpResponseList<ToolsLib2Entity> getToolInfo(@PathVariable("qrcode") String qrcode) {
		try {			
			
			return new HttpResponseList<ToolsLib2Entity>(toolslibService.getToolInfo(qrcode));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLib2Entity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据工器具类别获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByTool", method = RequestMethod.POST)
	public HttpResponseList<ToolsLib2Entity> getListByTool(@RequestParam("tooltype") String tooltype) {
		try {			
			
			return new HttpResponseList<ToolsLib2Entity>(toolslibService.getListByTool(tooltype));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLib2Entity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据班组获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByClass", method = RequestMethod.POST)
	public HttpResponseList<ToolsLib2Entity> getListByClass(@RequestParam("groupname") String groupname) {
		try {			
			
			return new HttpResponseList<ToolsLib2Entity>(toolslibService.getListByClass(groupname));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLib2Entity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据三级单位供电所获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListBySunit", method = RequestMethod.POST)
	public HttpResponseList<ToolsLib2Entity> getListBySunit(@RequestParam("sunits") String sunits) {
		try {			
			
			return new HttpResponseList<ToolsLib2Entity>(toolslibService.getListBySunit(sunits));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLib2Entity>(ResponseStatusCode.C400);
		}
	}
}

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
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.entity.ToolsLibViewEntity;
import com.qwx.service.ToolsLibViewService;


@RestController
@RequestMapping(value = "/toolslibv")
public class ToolsLibViewController extends BaseController<ToolsLib2Entity> {

	
	public ToolsLibViewController() {
		tableName = "toolslib_v";
	}

	@Resource
	ToolsLibViewService toolslibvService;
	/**
	 * 获取工器具列表
	 */
	@RequestMapping(value = "/getToolList", method = RequestMethod.GET)
	public HttpResponsePageList<ToolsLibViewEntity> getToolList(@RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getToolList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 获取待检测工器具列表
	 */
	@RequestMapping(value = "/getToolListByState", method = RequestMethod.POST)
	public HttpResponsePageList<ToolsLibViewEntity> getToolListByState(@RequestParam("state") String state,@RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getToolListByState(state, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 获取安全工器具信息
	 */
	@RequestMapping(value = "/getToolInfo/{qrcode}", method = RequestMethod.POST)
	public HttpResponseList<ToolsLibViewEntity> getToolInfo(@PathVariable("qrcode") String qrcode) {
		try {			
			return new HttpResponseList<ToolsLibViewEntity>(toolslibvService.getToolInfo(qrcode));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 获取安全工器具信息（微信扫描）
	 */
	@RequestMapping(value = "/getToolInfoByWx/{qrcode}", method = RequestMethod.GET)
	public HttpResponseList<ToolsLibViewEntity> getToolInfoByWx(@PathVariable("qrcode") String qrcode) {
		try {			
			return new HttpResponseList<ToolsLibViewEntity>(toolslibvService.getToolInfo(qrcode));
		} catch (Exception e) {
			return new HttpResponseList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据工器具类别获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByTool", method = RequestMethod.POST)
	public HttpResponsePageList<ToolsLibViewEntity> getListByTool(@RequestParam("tooltype") String tooltype, @RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListByTool(tooltype, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据班组获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByClass", method = RequestMethod.POST)
	public HttpResponsePageList<ToolsLibViewEntity> getListByClass(@RequestParam("groupname") String groupname, @RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListByClass(groupname, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据三级单位供电所获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListBySunit", method = RequestMethod.POST)
	public HttpResponsePageList<ToolsLibViewEntity> getListBySunit(@RequestParam("sunits") String sunits, @RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListBySunit(sunits, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据安全工器具名称模糊查询
	 */
	@RequestMapping(value = "/getListByToolname", method = RequestMethod.POST)
	public HttpResponsePageList<ToolsLibViewEntity> getListByToolname(@RequestParam("toolname") String toolname, @RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListByToolname(toolname, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据三级单位供电所获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByBatch", method = RequestMethod.GET)
	public HttpResponsePageList<ToolsLibViewEntity> getListByBatch(@RequestParam("batch") String batch, @RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListByBatch(batch, page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取安全工器具信息列表
	 */
	@RequestMapping(value = "/getListByFilter", method = RequestMethod.GET)
	public HttpResponsePageList<ToolsLibViewEntity> getListByFilter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<ToolsLibViewEntity>(toolslibvService.getListByFilter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<ToolsLibViewEntity>(ResponseStatusCode.C400);
		}
	}
}

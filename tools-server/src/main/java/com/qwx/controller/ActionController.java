package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.ActionEntity;
import com.qwx.service.ActionService;

@RestController
@RequestMapping(value = "/action")
public class ActionController extends BaseController<ActionEntity> {

	
	public ActionController() {
		tableName = "action";
	}

	@Resource
	ActionService actionService;
	/**
	 * 获取权限动作列表
	 */
	@RequestMapping(value = "/getActionList", method = RequestMethod.GET)
	public HttpResponsePageList<ActionEntity> getActionList(@RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<ActionEntity>(actionService.getActionList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<ActionEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取权限动作信息列表
	 */
	@RequestMapping(value = "/getActionListByFileter", method = RequestMethod.GET)
	public HttpResponsePageList<ActionEntity> getActionListByFileter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<ActionEntity>(actionService.getActionListByFileter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<ActionEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 根据筛选条件获取权限动作信息列表
	 */
	@RequestMapping(value = "/delAction", method = RequestMethod.POST)
	public HttpResponse<String> delAction(@RequestBody ActionEntity entity) {
		try {			
			
			return new HttpResponse<String>(actionService.delAction(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

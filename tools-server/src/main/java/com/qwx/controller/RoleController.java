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
import com.qwx.entity.RoleEntity;
import com.qwx.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController<RoleEntity> {

	
	public RoleController() {
		tableName = "role";
	}

	@Resource
	RoleService roleService;
	/**
	 * 获取用户列表
	 */
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	public HttpResponsePageList<RoleEntity> getRoleList(@RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<RoleEntity>(roleService.getRoleList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<RoleEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/getRoleListByFileter", method = RequestMethod.GET)
	public HttpResponsePageList<RoleEntity> getRoleListByFileter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<RoleEntity>(roleService.getRoleListByFileter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<RoleEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	public HttpResponse<String> delRole(@RequestBody RoleEntity entity) {
		try {			
			
			return new HttpResponse<String>(roleService.delRole(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

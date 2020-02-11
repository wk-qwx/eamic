package com.qwx.controller;

import java.util.List;

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
import com.qwx.entity.RoleActionEntity;
import com.qwx.service.RoleActionService;

@RestController
@RequestMapping(value = "/roleaction")
public class RoleActionController extends BaseController<RoleActionEntity> {

	
	public RoleActionController() {
		tableName = "role_action";
	}

	@Resource
	RoleActionService roleactionService;
	/**
	 * 获取角色和权限映射
	 */
	@RequestMapping(value = "/getRoleAction/{roleid}", method = RequestMethod.GET)
	public HttpResponseList<RoleActionEntity> getRoleAction(@PathVariable("roleid") String roleid) {
		try {			
			
			return new HttpResponseList<RoleActionEntity>(roleactionService.getRoleAction(roleid));
		} catch (Exception e) {
			return new HttpResponseList<RoleActionEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/getRoleListByFileter", method = RequestMethod.GET)
	public HttpResponsePageList<RoleActionEntity> getRoleListByFileter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<RoleActionEntity>(roleactionService.getRoleActionListByFileter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<RoleActionEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 删除角色权限
	 */
	@RequestMapping(value = "/delRoleAction", method = RequestMethod.POST)
	public HttpResponse<String> delRoleAction(@RequestBody List<RoleActionEntity> entitys) {
		try {			
			
			return new HttpResponse<String>(roleactionService.delRoleAction(entitys));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

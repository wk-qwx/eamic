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
import com.qwx.entity.RoleUserEntity;
import com.qwx.service.RoleUserService;

@RestController
@RequestMapping(value = "/roleuser")
public class RoleUserController extends BaseController<RoleUserEntity> {

	
	public RoleUserController() {
		tableName = "role_user";
	}

	@Resource
	RoleUserService roleuserService;
	/**
	 * 获取角色和用户映射
	 */
	@RequestMapping(value = "/getRoleUser/{roleid}", method = RequestMethod.GET)
	public HttpResponseList<RoleUserEntity> getRoleList(@PathVariable("roleid") String roleid) {
		try {			
			
			return new HttpResponseList<RoleUserEntity>(roleuserService.getRoleUser(roleid));
		} catch (Exception e) {
			return new HttpResponseList<RoleUserEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/getRoleListByFileter", method = RequestMethod.GET)
	public HttpResponsePageList<RoleUserEntity> getRoleListByFileter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<RoleUserEntity>(roleuserService.getRoleUserListByFileter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<RoleUserEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 删除角色用户
	 */
	@RequestMapping(value = "/delRoleUser", method = RequestMethod.POST)
	public HttpResponse<String> delRoleUser(@RequestBody List<RoleUserEntity> entitys) {
		try {			
			
			return new HttpResponse<String>(roleuserService.delRoleUser(entitys));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

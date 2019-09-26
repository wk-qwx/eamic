package com.qwx.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.BaseEntity;
import com.qwx.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<BaseEntity> {

	
	public UserController() {
		tableName = "\"ea_user\"";
	}

	@Resource
	UserService userService;

	/**
	 * 读取用户列表
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public HttpResponse<String>  getUsers() {
		try {			
			return new HttpResponse<String>(userService.getUsers());
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 用户信息验证登录
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public HttpResponse<String>  checkUser() {
		try {			
			return new HttpResponse<String>(userService.getUsers());
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	
}

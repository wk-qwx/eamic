package com.qwx.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.UserEntity;
import com.qwx.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

	
	public UserController() {
		tableName = "ea_user";
	}

	@Resource
	UserService userService;

	/**
	 * 读取用户列表
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public HttpResponse<String> getUsers() {
		try {			
			return new HttpResponse<String>(userService.getUsers());
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 用户信息验证登录
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public HttpResponse<String> checkUser(@RequestParam("username") String username,@RequestParam("pwd") String pwd) {
		try {
			//用户验证前先去缓存验证登录是否失效
			return new HttpResponse<String>(userService.checkUser(username,pwd));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	
}

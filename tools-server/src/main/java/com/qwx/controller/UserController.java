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
import com.qwx.entity.UserEntity;
import com.qwx.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

	
	public UserController() {
		tableName = "tl_user";
	}

	@Resource
	UserService userService;
	/**
	 * 获取用户列表
	 */
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public HttpResponsePageList<UserEntity> getUserList(@RequestParam("page") String page, @RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<UserEntity>(userService.getUserList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<UserEntity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/getUserListByFileter", method = RequestMethod.GET)
	public HttpResponsePageList<UserEntity> getUserListByFileter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<UserEntity>(userService.getUserListByFileter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<UserEntity>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 根据筛选条件获取用户信息列表
	 */
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	public HttpResponse<String> delUser(@RequestBody UserEntity entity) {
		try {			
			
			return new HttpResponse<String>(userService.delUser(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

package com.qwx.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.UserEntity;
import com.qwx.service.UserService;
import com.qwx.util.RedisUtil;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

	
	public UserController() {
		tableName = "ea_user";
	}

	@Resource
	UserService userService;
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 用户信息验证登录
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public HttpResponse<String> checkUser(@RequestParam("username") String username,@RequestParam("pwd") String pwd,@RequestParam("token") String oldtoken) {
		try {
			String token = "";
			String res ="";
			if((username != null && !"".equals(username))&&(pwd != null && !"".equals(pwd))){
				List<UserEntity> user = userService.checkUser(username,pwd);
				if(user.size() == 0 || user == null)return new HttpResponse<String>("[]");
					
				long now = System.currentTimeMillis();
				SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
				long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
				
				//当前时间  距离当天晚上23:59:59  秒数 也就是今天还剩多少秒
		        long TimeNext = 24*60*60 - overTime;
		        
				token = user.get(0).getId();		
				redisUtil.set(user.get(0).getId(), token, TimeNext);//写入redis 当前时间  距离当天晚上23:59:59  秒数
				//redisUtil.set(user.get(0).getId(), token, 86400);//写入redis计时一天
				return new HttpResponse<String>(
						ResponseStatusCode.C200,
						ResponseStatusCode.getMessage(ResponseStatusCode.C200),
						token,JSONObject.toJSONString(user));
			}
			System.out.println("时效验证token："+oldtoken);
			//用户验证前先去缓存验证登录是否失效
			if(redisUtil.hasKey(oldtoken))//判断key是否存在
				token = redisUtil.get(oldtoken).toString();
			if(token == "")
				res = "false";
			else
				res = "true";
			return new HttpResponse<String>(
					ResponseStatusCode.C200,
					ResponseStatusCode.getMessage(ResponseStatusCode.C200),
					res);
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	
}

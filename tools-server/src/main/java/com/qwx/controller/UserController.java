package com.qwx.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.entity.LoginRecordEntity;
import com.qwx.entity.UserEntity;
import com.qwx.entity.UserViewEntity;
import com.qwx.service.UserService;
import com.qwx.util.RedisUtil;
import com.qwx.util.StringUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

	
	public UserController() {
		tableName = "tl_user";
	}

	@Resource
	UserService userService;
	@Autowired
	private RedisUtil redisUtil;
	@Resource
	BasePagingAndSortingRepository<LoginRecordEntity, String> loginrecordDao;
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
	/**
	 * 用户信息验证登录
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public HttpResponse<String> checkUser(@RequestParam("username") String username,@RequestParam("pwd") String pwd,@RequestParam("token") String oldtoken) {
		try {
			String token = "";
			String res ="";
			if((username != null && !"".equals(username))&&(pwd != null && !"".equals(pwd))){
				List<UserViewEntity> user = userService.checkUser(username,pwd);
				if(user.size() == 0 || user == null)return new HttpResponse<String>("[]");
					
				long now = System.currentTimeMillis();
				SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
				long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
				
				//当前时间  距离当天晚上23:59:59  秒数 也就是今天还剩多少秒
		        long TimeNext = 24*60*60 - overTime;
		        
				token = StringUtil.getUUID();		
				redisUtil.set(token, user.get(0).getId(), TimeNext);//写入redis 当前时间  距离当天晚上23:59:59  秒数
				//redisUtil.set(user.get(0).getId(), token, 86400);//写入redis计时一天
				
				LoginRecordEntity row = new LoginRecordEntity();
				row.setLogintoken(token);
				row.setLogintype(oldtoken);
				row.setUserid(user.get(0).getId());
				row.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
				loginrecordDao.save(row);//插入登录日志
				
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

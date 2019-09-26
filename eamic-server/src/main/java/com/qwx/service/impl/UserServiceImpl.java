package com.qwx.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.qwx.dao.UserDao;
import com.qwx.database.BaseService;
import com.qwx.entity.UserEntity;
import com.qwx.service.UserService;

import net.sf.json.JSONArray;

/**
 * 用户服务
 * @author kal02
 *
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity> implements UserService{


	public UserServiceImpl() {
		tableName = "\"ea_user\"";
	}
	/**
	 * 获取所有用户
	 */
	public String getUsers() {		
		
		try{
			String sql="select * from ea_user";
			List<UserEntity> userlist = getBySql(sql,UserEntity.class);
			String json=JSONArray.fromObject(userlist).toString();
			return json;
		}catch(Exception e){
		
		}
		return "false";
	}
	

}

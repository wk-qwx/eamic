package com.qwx.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.UserEntity;
import com.qwx.service.UserService;

/**
 * 用户服务
 * @author kal02
 *
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity> implements UserService{


	public UserServiceImpl() {
		tableName = "ea_user";
	}
	
	/**
	 * 用户验证
	 * @param username 用户名
	 * @param pwd 密码
	 * @return 返回数据行和token验证码
	 */
	public List<UserEntity> checkUser(String username,String pwd){
		try {
			String sql="select * from ea_user where username = '"+username+"' and password = '"+pwd+"'";
			List<UserEntity> user = getBySql(sql,UserEntity.class);	
				
			return user;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}

}

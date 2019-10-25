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
	 * @return 1:验证成功  0:验证失败 2:token失效（在控制层验证返回）
	 */
	public String checkUser(String username,String pwd){
		try {
			String sql="select * from ea_user where username = '"+username+"' and password = '"+pwd+"'";
			List<UserEntity> user = getBySql(sql,UserEntity.class);
			if(user.size() == 1){//验证成功后读入ridis缓存
				return "1";
			}
			if(user.size() == 0)return "0";
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return "登录异常";
	}

}

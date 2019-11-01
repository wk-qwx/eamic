package com.qwx.service;

import java.util.List;

import com.qwx.entity.UserEntity;

/**
 * 用户信息接口
 * @author kal02
 *
 */
public interface UserService {

	/**
	 * 用户验证
	 * @param username 用户名
	 * @param pwd 密码
	 * @return true or false
	 */
	public List<UserEntity> checkUser(String username,String pwd);
}

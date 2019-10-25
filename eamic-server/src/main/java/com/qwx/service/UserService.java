package com.qwx.service;


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
	public String checkUser(String username,String pwd);
}

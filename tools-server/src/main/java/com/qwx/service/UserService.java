package com.qwx.service;

import com.qwx.bean.PageList;
import com.qwx.entity.UserEntity;

/**
 * 用户信息接口
 * @author kal02
 *
 */
public interface UserService {
	/**
	 * 获取用户列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public PageList<UserEntity> getUserList(String page, String limit);
	/**
	 * 筛选条件过滤用户列表
	 * @param page
	 * @param limit
	 * @param whereStr
	 * @return
	 */
	public PageList<UserEntity> getUserListByFileter(String page, String limit, String whereStr);
	/**
	 * 删除用户
	 * @param entity
	 * @return
	 */
	public String delUser(UserEntity entity);
}

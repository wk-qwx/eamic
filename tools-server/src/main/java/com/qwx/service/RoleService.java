package com.qwx.service;

import com.qwx.bean.PageList;
import com.qwx.entity.RoleEntity;

/**
 * 角色信息接口
 * @author kal02
 *
 */
public interface RoleService {
	/**
	 * 获取角色列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public PageList<RoleEntity> getRoleList(String page, String limit);
	/**
	 * 筛选条件过滤角色列表
	 * @param page
	 * @param limit
	 * @param whereStr
	 * @return
	 */
	public PageList<RoleEntity> getRoleListByFileter(String page, String limit, String whereStr);
	/**
	 * 删除角色
	 * @param entity
	 * @return
	 */
	public String delRole(RoleEntity entity);
}

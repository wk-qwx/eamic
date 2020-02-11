package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.RoleUserEntity;

/**
 * 角色信息接口
 * @author kal02
 *
 */
public interface RoleUserService {
	/**
	 * 获取角色和用户映射
	 * @param 角色id
	 * @return
	 */
	public List<RoleUserEntity> getRoleUser(String roleid);
	/**
	 * 筛选条件过滤角色列表
	 * @param page
	 * @param limit
	 * @param whereStr
	 * @return
	 */
	public PageList<RoleUserEntity> getRoleUserListByFileter(String page, String limit, String whereStr);
	/**
	 * 删除角色用户
	 * @param entitys
	 * @return
	 */
	public String delRoleUser(List<RoleUserEntity> entitys);
}

package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.RoleActionEntity;

/**
 * 角色信息接口
 * @author kal02
 *
 */
public interface RoleActionService {
	/**
	 * 获取角色列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public List<RoleActionEntity> getRoleAction(String roleid);
	/**
	 * 筛选条件过滤角色列表
	 * @param page
	 * @param limit
	 * @param whereStr
	 * @return
	 */
	public PageList<RoleActionEntity> getRoleActionListByFileter(String page, String limit, String whereStr);
	/**
	 * 删除角色
	 * @param entity
	 * @return
	 */
	public String delRoleAction(List<RoleActionEntity> entitys);
}

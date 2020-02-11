package com.qwx.service;

import com.qwx.bean.PageList;
import com.qwx.entity.ActionEntity;

/**
 * 权限动作信息接口
 * @author kal02
 *
 */
public interface ActionService {
	/**
	 * 获取权限动作列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public PageList<ActionEntity> getActionList(String page, String limit);
	/**
	 * 筛选条件过滤权限动作列表
	 * @param page
	 * @param limit
	 * @param whereStr
	 * @return
	 */
	public PageList<ActionEntity> getActionListByFileter(String page, String limit, String whereStr);
	/**
	 * 删除权限动作
	 * @param entity
	 * @return
	 */
	public String delAction(ActionEntity entity);
}

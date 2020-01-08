package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.ToolsLibViewEntity;

/**
 * 工器具信息接口
 * @author kal02
 *
 */
public interface ToolsLibViewService {
	/**
	 * 获取工器具列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public PageList<ToolsLibViewEntity> getToolList(String page, String limit);
	/**
	 * 根据工器具状态获取工器具列表
	 * @param page 页码
	 * @param limit 页大小
	 * @param state 工器具状态
	 * @return
	 */
	public PageList<ToolsLibViewEntity> getToolListByState(String state, String page, String limit);
	/**
	 * 获取工器具信息
	 * @param qrcode
	 * @return
	 */
	public List<ToolsLibViewEntity> getToolInfo(String qrcode);
	/**
	 * 根据工器具类别获取列表
	 * @param tooltype
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByTool(String tooltype, String page, String limit);
	/**
	 * 根据班组获取列表
	 * @param groupname
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByClass(String groupname, String page, String limit);
	/**
	 * 根据三级供电所获取列表
	 * @param sunits
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListBySunit(String sunits, String page, String limit);
	/**
	 * 根据工器具名称模糊查询
	 * @param toolname
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByToolname(String toolname, String page, String limit);
	/**
	 * 根据批次号获取列表
	 * @param sunits
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByBatch(String batch, String page, String limit);
	/**
	 * 根据筛选条件
	 * @param pageIndex
	 * @param pageSize
	 * @param whereStr
	 * @return 工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByFilter(String page, String limit, String whereStr);
}

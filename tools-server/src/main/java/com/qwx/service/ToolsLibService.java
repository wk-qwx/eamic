package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.ToolsLib2Entity;

/**
 * 工器具信息接口
 * @author kal02
 *
 */
public interface ToolsLibService {
	/**
	 * 获取工器具列表
	 * @param page 页码
	 * @param limit 页大小
	 * @return
	 */
	public PageList<ToolsLib2Entity> getToolList(String page, String limit);
	/**
	 * 获取工器具信息
	 * @param qrcode
	 * @return
	 */
	public List<ToolsLib2Entity> getToolInfo(String qrcode);
	/**
	 * 根据工器具类别获取列表
	 * @param tooltype
	 * @return 工器具列表
	 */
	public List<ToolsLib2Entity> getListByTool(String tooltype);
	/**
	 * 根据班组获取列表
	 * @param groupname
	 * @return 工器具列表
	 */
	public List<ToolsLib2Entity> getListByClass(String groupname);
	/**
	 * 根据三级供电所获取列表
	 * @param sunits
	 * @return 工器具列表
	 */
	public List<ToolsLib2Entity> getListBySunit(String sunits);
}

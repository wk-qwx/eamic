package com.qwx.service;

import java.util.List;

import com.qwx.entity.ToolsLib2Entity;

/**
 * 工器具信息接口
 * @author kal02
 *
 */
public interface ToolsLibService {
	/**
	 * 安全工器具数据更新
	 * @param entity
	 * @return 更新数据的id
	 */
	public String updatex(ToolsLib2Entity entity);
	/**
	 * 合格标签下载
	 * @param entitys
	 * @return
	 */
	public String uptostandard(List<ToolsLib2Entity> entitys);
	
}

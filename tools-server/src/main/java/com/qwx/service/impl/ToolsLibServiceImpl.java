package com.qwx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.entity.ToolsLibEntity;
import com.qwx.service.ToolsLibService;

/**
 * 工器具管理服务
 * @author kal02
 *
 */
@Service
public class ToolsLibServiceImpl extends BaseService<ToolsLib2Entity> implements ToolsLibService{
	
	
	public ToolsLibServiceImpl() {
		tableName = "tl_toolslib";
	}
	/**
	 * 获取工器具信息
	 */
	public List<ToolsLib2Entity> getToolInfo(String qrcode) {
		String sql = "select * from tl_toolslib where qrcode = '"+qrcode+"'";
		return getBySql(sql);
	}
	/**
	 * 根据类别获取工器具列表
	 */
	public List<ToolsLib2Entity> getListByTool(String tooltype){
		String sql = "select * from tl_toolslib where tooltype = '"+tooltype+"'";
		return getBySql(sql);
	}
	/**
	 * 根据班组获取工器具列表
	 */
	public List<ToolsLib2Entity> getListByClass(String groupname){
		String sql = "select * from tl_toolslib where groupname = '"+groupname+"'";
		return getBySql(sql);
	}
	/**
	 * 根据三级供电所获取工器具列表
	 */
	public List<ToolsLib2Entity> getListBySunit(String sunits){
		String sql = "select * from tl_toolslib where sunits = '"+sunits+"'";
		return getBySql(sql);
	}
}

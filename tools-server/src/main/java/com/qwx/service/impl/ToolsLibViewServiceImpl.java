package com.qwx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.entity.ToolsLibViewEntity;
import com.qwx.service.ToolsLibService;
import com.qwx.service.ToolsLibViewService;

/**
 * 工器具管理服务
 * @author kal02
 *
 */
@Service
public class ToolsLibViewServiceImpl extends BaseService<ToolsLibViewEntity> implements ToolsLibViewService{
	
	
	public ToolsLibViewServiceImpl() {
		tableName = "toolslib_v";
	}
	/**
	 *获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getToolList(String page, String limit) {
		String sql = "select * from toolslib_v order by devicestate2 desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 *获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getToolListByState(String state, String page, String limit) {
		String sql = "select * from toolslib_v where devicestate2 = '"+state+"'";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 获取工器具信息
	 */
	public PageList<ToolsLibViewEntity> getToolInfo(String qrcode, String page, String limit) {
		String sql = "select * from toolslib_v where qrcode = '"+qrcode+"'";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 根据类别获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByTool(String tooltype, String page, String limit){
		String sql = "select * from toolslib_v where tooltype = '"+tooltype+"' order by devicestate2 desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 根据批次获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByBatch(String batch, String page, String limit){
		String sql = "select * from toolslib_v where substring(qrcode,1,6) = '"+batch+"' order by devicestate2 desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 根据班组获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListByClass(String groupname, String page, String limit){
		String sql = "select * from toolslib_v where groupname = '"+groupname+"' order by devicestate2 desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 根据三级供电所获取工器具列表
	 */
	public PageList<ToolsLibViewEntity> getListBySunit(String sunits, String page, String limit){
		String sql = "select * from toolslib_v where sunits = '"+sunits+"' order by devicestate2 desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 工器具信息筛选查询分页列表
	 */
	public PageList<ToolsLibViewEntity> getListByFilter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from toolslib_v ORDER BY devicestate2 desc";			
		}else{
			sql = "select * from toolslib_v where " + whereStr + " ORDER BY devicestate2 desc";
		}
		try{
			//返回分页列表
			return getPageBySql(page,limit,sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
}

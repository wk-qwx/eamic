package com.qwx.service;



/**
 * 缺陷信息接口
 * @author kal02
 *
 */
public interface DefectLibService {
	
	/**
	 * 缺陷查询列表分页
	 * @param pageIndex
	 * @param pageSize
	 * @param groupid
	 * @param where
	 * @return 缺陷列表
	 */
	public String getDefectsByFilter(String pageIndex, String pageSize, String groupid, String where);
}

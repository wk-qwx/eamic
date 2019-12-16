package com.qwx.service;


import com.qwx.entity.DefectEntity;
import com.qwx.util.ConfigUtil;

/**
 * 缺陷信息接口
 * @author kal02
 *
 */
public interface DefectService {
	
	
	/**
	 * 提交缺陷信息
	 * @param entity
	 * @return 缺陷id字符串
	 */
	public String submit(DefectEntity entity);
	/**
	 * 修改缺陷信息
	 * @param entity
	 * @return 缺陷id字符串
	 */
	public String updatex(DefectEntity entity);
	
	/**
	 * 缺陷删除
	 */	
	public String delDefect(String id);
}

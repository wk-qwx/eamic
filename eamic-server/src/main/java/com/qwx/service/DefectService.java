package com.qwx.service;


import com.qwx.entity.DefectEntity;
import com.qwx.util.ConfigUtil;

/**
 * 缺陷信息接口
 * @author kal02
 *
 */
public interface DefectService {
	//照片存放路径
	public static String PHOTOPATH = ConfigUtil.getProperty("filePath");
	/**
	 * 提交缺陷信息
	 * @param entity
	 * @return 缺陷id字符串
	 */
	public String submit(DefectEntity entity);
	/**
	 * 缺陷列表导出exel
	 */
	public String downloadexel();
}

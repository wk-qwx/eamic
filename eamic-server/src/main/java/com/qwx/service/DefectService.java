package com.qwx.service;

import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.PageList;
import com.qwx.entity.DefectEntity;
import com.qwx.util.ConfigUtil;

/**
 * 缺陷信息接口
 * @author kal02
 *
 */
public interface DefectService {
	//照片存放路径
	public static String PHOTOPATH = ConfigUtil.getProperty("photoPath");
	/**
	 * 获取所有缺陷信息
	 * @return 缺陷列表
	 */
	public PageList<DefectEntity> getDefects();
	/**
	 * 提交缺陷信息
	 * @return 缺陷ID
	 */
	public String submit(DefectEntity entity);
	/**
	 * 通过id获取缺陷信息
	 * @return 未消缺状态量扣分总数
	 */
	public String getCurrentScore(String jsonstr);
}

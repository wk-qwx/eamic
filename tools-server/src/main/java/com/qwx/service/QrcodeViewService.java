package com.qwx.service;

import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;

/**
 * 二维码管理信息接口
 * @author kal02
 *
 */
public interface QrcodeViewService {
	/**
	 * 二维码信息列表
	 * @return
	 */	
	public PageList<QrcodeView2Entity> getQrcodeView(String page, String limit);
	/**
	 * 二维码导出下载
	 * @param entitys 导出对象
	 * @param mode 导出方式
	 * @return
	 */
	public String export(QrcodeView2Entity entity);
}

package com.qwx.service;


import java.util.List;

import com.qwx.bean.PageList;
import com.qwx.entity.Qrcode2Entity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;


/**
 * 二维码管理信息接口
 * @author kal02
 *
 */
public interface QrcodeService {
	/**
	 * 二维码文件列表
	 * @param page 页码
	 * @param limit 页的大小
	 * @return 二维码文件列表
	 */
	public PageList<Qrcode2Entity> getQrcodeFileList(String page, String limit);
	/**
	 * 根据筛选条件二维码文件列表
	 * @param page 页码
	 * @param limit 页的大小
	 * @return 二维码文件列表
	 */
	public PageList<Qrcode2Entity> getListByFilter(String page, String limit, String whereStr);
	/**
	 * 1.二维码创建
	 * 2.工器具导入
	 * @param jsonstr 生成对象
	 * @return
	 */	
	public String submit(QrcodeView2Entity entity);
	
}

package com.qwx.service;

import java.util.List;
import com.qwx.bean.PageList;
import com.qwx.entity.QrcodeView2Entity;

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
	 * @param flag 导出标识
	 * @return
	 */
	public String downloadexel(List<QrcodeView2Entity> entitys, String flag);
	/**
	 * 二维码导出下载
	 * @param qrid 文件id
	 * @return
	 */
	public String downloadexel(String qrid);
	/**
	 * 根据筛选条件二维码列表
	 * @param page 页码
	 * @param limit 页的大小
	 * @return 二维码文件列表
	 */
	public PageList<QrcodeView2Entity> getListByFilter(String page, String limit, String whereStr);
}

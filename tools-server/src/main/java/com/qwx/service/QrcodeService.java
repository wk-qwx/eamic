package com.qwx.service;


import java.util.List;

import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;


/**
 * 二维码管理信息接口
 * @author kal02
 *
 */
public interface QrcodeService {
	/**
	 * 1.二维码创建
	 * 2.工器具导入
	 * @param jsonstr 生成对象
	 * @return
	 */	

	public String submit(QrcodeView2Entity entity);
	
}

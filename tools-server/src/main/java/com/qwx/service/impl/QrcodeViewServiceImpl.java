package com.qwx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.service.QrcodeViewService;
import com.qwx.util.ConfigUtil;
import com.qwx.util.QRCodeUtil;

/**
 * 二维码管理服务
 * @author kal02
 *
 */

@Service
public class QrcodeViewServiceImpl extends BaseService<QrcodeView2Entity> implements QrcodeViewService{

	public QrcodeViewServiceImpl() {
		tableName = "qrcode_v";
	}
	private static final String CODEPATH = ConfigUtil.getProperty("filePath")+"\\codefile\\";
	/**
	 *二维码列表
	 */
	public PageList<QrcodeView2Entity> getQrcodeView(String page, String limit) {
		String sql = "select * from qrcode_v ";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 二维码文件下载导出
	 */
	public String export(QrcodeView2Entity entity){
		
		String filepath;
		try {
			filepath = CODEPATH + entity.getBatch() + "-" + entity.getSunits()
					  			+"("+entity.getTooltype()+").xls";
			String sql = "select * from qrcode_v where batch = '"+entity.getBatch()+"'";
			List<QrcodeView2Entity> list = getBySql(sql);
			for(int i = 0; i< list.size(); i++){
				String qrcode = list.get(i).getQrcode();
				StringBuilder str = new StringBuilder(qrcode);
				int k = str.length() / 3;
			    int j = str.length() % 3;
			 
			    for (int x = (j == 0 ? k - 1 : k); x > 0; x--) {
			    	str = str.insert(x * 3," ");
			    }
				//生成二维码图片
			    QRCodeUtil.getQRCode(qrcode, list.get(i).getSunits(), "("+str.toString()+")");
				
			}
			//返回下载路径
			return QRCodeUtil.inputExel(filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

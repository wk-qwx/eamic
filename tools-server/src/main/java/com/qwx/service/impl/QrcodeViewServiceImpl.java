package com.qwx.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.Qrcode2Entity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.service.QrcodeViewService;
import com.qwx.util.ConfigUtil;
import com.qwx.util.ExcelUtil;

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
	private static final String CODEPATH = ConfigUtil.getProperty("filePath");
	/**
	 *二维码列表
	 */
	public PageList<QrcodeView2Entity> getQrcodeView(String page, String limit) {
		String sql = "select * from qrcode_v ";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 二维码筛选查询分页列表
	 */
	public PageList<QrcodeView2Entity> getListByFilter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from qrcode_v ORDER BY createtime desc";			
		}else{
			sql = "select * from qrcode_v where " + whereStr + " ORDER BY createtime desc";
		}
		try{
			//返回分页列表
			return getPageBySql(page,limit,sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * 二维码文件下载导出
	 */
	public String downloadexel(List<QrcodeView2Entity> entitys, String flag){		
		ExcelUtil<QrcodeView2Entity> eu = new ExcelUtil<QrcodeView2Entity>();
		String filepath = CODEPATH + "\\codefile\\安全工器具二维码打印模板.xls";
		if("1".equals(flag)){
			String sql = "select * from qrcode_v where batch = '"+entitys.get(0).getBatch()+"'";
			List<QrcodeView2Entity> list = getBySql(sql);
			if(list.size()==0)return "暂无数据";
			Map<Integer,String> cells = new HashMap<>();
			Map<Integer,String> cells2 = new HashMap<>();
			cells.put(1, "二维码");cells2.put(1, "qrcode");
			cells.put(2, "code");cells2.put(2, "code");
			cells.put(3, "三级单位");cells2.put(3, "sunits");
			cells.put(4, "所属分组");cells2.put(4, "groupname");
			cells.put(5, "工器具类别");cells2.put(5, "tooltype");
			cells.put(6, "工器具名称");cells2.put(6, "toolname");
			try {
				File targetFile = new File(filepath);
				OutputStream out = new FileOutputStream(targetFile);
				//导出打印文件
				eu.export("二维码打印列表",cells,cells2,list, "yyyy-MM-dd", out);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("2".equals(flag)){			
			Map<Integer,String> cells = new HashMap<>();
			Map<Integer,String> cells2 = new HashMap<>();
			cells.put(1, "二维码");cells2.put(1, "qrcode");
			cells.put(2, "code");cells2.put(2, "code");
			cells.put(3, "三级单位");cells2.put(3, "sunits");
			cells.put(4, "所属分组");cells2.put(4, "groupname");
			cells.put(5, "工器具类别");cells2.put(5, "tooltype");
			cells.put(6, "工器具名称");cells2.put(6, "toolname");
			try {
				File targetFile = new File(filepath);
				OutputStream out = new FileOutputStream(targetFile);
				//导出打印文件
				eu.export("二维码打印列表",cells,cells2,entitys, "yyyy-MM-dd", out);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//返回下载路径
		return filepath.replace(CODEPATH, "\\static");
	}
	/**
	 * 二维码文件下载导出
	 */
	public String downloadexel(String qrid){
		ExcelUtil<QrcodeView2Entity> eu = new ExcelUtil<QrcodeView2Entity>();
		String filepath = null;	
		String sql = "select * from qrcode_v where qrid = '"+qrid+"'";
		List<QrcodeView2Entity> list = getBySql(sql);
		if(list.size()==0)return "暂无数据";
		Map<Integer,String> cells = new HashMap<>();
		Map<Integer,String> cells2 = new HashMap<>();
		cells.put(1, "二维码");cells2.put(1, "qrcode");
		cells.put(2, "code");cells2.put(2, "code");
		cells.put(3, "三级单位");cells2.put(3, "sunits");
		cells.put(4, "所属分组");cells2.put(4, "groupname");
		cells.put(5, "工器具类别");cells2.put(5, "tooltype");
		cells.put(6, "工器具名称");cells2.put(6, "toolname");
		try {
			filepath = CODEPATH + "\\codefile\\安全工器具二维码打印模板-"+list.get(0).getToolname()+".xls";
			File targetFile = new File(filepath);
			OutputStream out = new FileOutputStream(targetFile);
			//导出打印文件
			eu.export("二维码打印列表",cells,cells2,list, "yyyy-MM-dd", out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回下载路径
		return filepath.replace(CODEPATH, "\\static");
	}
	/**
	 * 二维码文件下载导出
	 *//*
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
	}*/
}

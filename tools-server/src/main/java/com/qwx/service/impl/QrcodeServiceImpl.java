package com.qwx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.Qrcode2Entity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.service.QrcodeService;

/**
 * 二维码管理服务
 * @author kal02
 *
 */

@Service
public class QrcodeServiceImpl extends BaseService<Qrcode2Entity> implements QrcodeService{

	@Resource
	BasePagingAndSortingRepository<ToolsLib2Entity, String> toolslibDao;
	public QrcodeServiceImpl() {
		tableName = "tl_qrcode";
	}
	/**
     * 存放路径
     */   
    private static String batch = "";
    private static ToolsLib2Entity row = null;
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
	/**
	 *二维码文件
	 */
	public PageList<Qrcode2Entity> getQrcodeFileList(String page, String limit) {
		String sql = "select * from tl_qrcode order by createtime desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 二维码文件筛选查询分页列表
	 */
	public PageList<Qrcode2Entity> getListByFilter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from tl_qrcode ORDER BY createtime desc";			
		}else{
			sql = "select * from tl_qrcode where " + whereStr + " ORDER BY createtime desc";
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
	 * 1.二维码创建
	 * 2.工器具导入
	 */
	public String submit(QrcodeView2Entity entity) {			
		try{					
			int item = Integer.valueOf(entity.getItem());//生成二维码个数	
			int k = 0;
			String qrcode;
			String timeStr = String.valueOf(System.currentTimeMillis());//系统当前时间戳作为二维码
			batch = timeStr.substring(timeStr.length()-9,timeStr.length()-3);//批次号
			List<ToolsLib2Entity> rows = new ArrayList<ToolsLib2Entity>();
			while(k < item){
				k++;				
			    if (k < 10) {
			    	qrcode = batch+"00" + k;
			    } else if (k >= 100) {
			    	qrcode = batch+k;
			    } else{
			    	qrcode = batch+"0" + k;
			    } 
		        //相应生成一件工器具
		        row = new ToolsLib2Entity();
				row.setUnit(entity.getUnit());
				row.setQrcode(qrcode);
				row.setSunits(entity.getSunits());
				row.setTooltype(entity.getTooltype());
				row.setToolname(entity.getToolname()+"("+qrcode.substring(5)+")");
				row.setUselife(entity.getUselife());
				row.setGroupname(entity.getGroupname());
				row.setDevicestate(entity.getDevicestate());
				row.setNewcheck(entity.getNewcheck());
				row.setLastcheck(entity.getLastcheck());
				row.setIscheck("0");//是否检测
				row.setScale(entity.getScale());//规格、电压等级
				rows.add(row);
			}
			//最后记录生成二维码文件			
			Qrcode2Entity qr = new Qrcode2Entity();	
			Date dNow = new Date();
			qr.setUnit(entity.getUnit());
			qr.setSunits(entity.getSunits());
			qr.setGroupname(entity.getGroupname());
			qr.setTooltype(entity.getTooltype());
			qr.setCreatetime(ft.format(dNow));
			qr.setItem(entity.getItem());
			qr.setBatch(batch);
			qr.setRemarks(entity.getRemarks());
			toolslibDao.save(rows);
			return add(qr);
		}catch(Exception e){			
			e.printStackTrace();
		}
		return null;
	}
	
}

package com.qwx.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.service.ToolsLibService;
import com.qwx.util.ConfigUtil;
import com.qwx.util.ExcelUtil;

/**
 * 工器具管理服务
 * @author kal02
 *
 */
@Service
public class ToolsLibServiceImpl extends BaseService<ToolsLib2Entity> implements ToolsLibService{
	
	private static final String CODEPATH = ConfigUtil.getProperty("filePath");
	
	public ToolsLibServiceImpl() {
		tableName = "tl_toolslib";
	}
	/**
	 *安全工器具更新
	 */
	public String updatex(ToolsLib2Entity entity) {
		return update(entity);
	}
	public String uptostandard(List<ToolsLib2Entity> entitys){
		ExcelUtil<ToolsLib2Entity> eu = new ExcelUtil<ToolsLib2Entity>();
		String filepath = null;	
		if(entitys.size()==0)return "暂无生成数据";
		Map<Integer,String> cells = new HashMap();
		Map<Integer,String> cells2 = new HashMap();
		cells.put(1, "二维码");cells2.put(1, "qrcode");		
		cells.put(2, "工器具名称");cells2.put(2, "toolname");
		cells.put(3, "试验日期");cells2.put(3, "lastcheck");
		cells.put(4, "有效日期");cells2.put(4, "newcheck");
		cells.put(5, "三级单位");cells2.put(5, "sunits");
		cells.put(6, "所属班组");cells2.put(6, "groupname");
		cells.put(7, "工器具类别");cells2.put(7, "tooltype");
		try {
			filepath = CODEPATH + "\\codefile\\安全工器具合格标签打印模板-"+entitys.get(0).getToolname()+".xls";
			File targetFile = new File(filepath);
			OutputStream out = new FileOutputStream(targetFile);
			//导出打印文件
			eu.export("合格标签打印列表",cells,cells2,entitys, "yyyy-MM-dd", out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回下载路径
		return filepath.replace(CODEPATH, "\\static");
	}
}

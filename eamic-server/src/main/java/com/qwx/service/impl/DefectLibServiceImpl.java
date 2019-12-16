package com.qwx.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectLibEntity;
import com.qwx.service.DefectLibService;
import com.qwx.util.ConfigUtil;
import com.qwx.util.ExcelUtil;

/**
 * 缺陷信息服务
 * @author kal02
 *
 */
@Service
public class DefectLibServiceImpl extends BaseService<DefectLibEntity> implements DefectLibService{

	public DefectLibServiceImpl() {
		tableName = "ea_defectlib_v";
	}
	//文件存放路径
	public static final String FILEPATH = ConfigUtil.getProperty("filePath");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
	/**
	 * 缺陷信息筛选查询分页列表
	 */
	public String getDefectsByFilter(String pageIndex, String pageSize, String groupid, String whereStr){
		String sql = "";
		if(whereStr.equals("null")){
			if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))
				sql = "select * from ea_defectlib_v where groupid ='"+groupid+"' ORDER BY finddate desc";				
			else			
				sql = "select * from ea_defectlib_v ORDER BY finddate desc";			
		}else{
			JSONObject jsonobject = JSONObject.parseObject(whereStr);
			if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))
				sql = "select * from ea_defectlib_v where groupid ='"+groupid+"' and "+jsonobject.getString("where") + " ORDER BY finddate desc";
			else								
				sql = "select * from ea_defectlib_v where "+jsonobject.getString("where") + " ORDER BY finddate desc";
		}
		try{
			//返回分页列表
			PageList<DefectLibEntity> pagelist = getPageBySql(pageIndex,pageSize,sql);
			return JSONObject.toJSONString(pagelist,SerializerFeature.WriteMapNullValue);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}		
	}
	/**
	 * 缺陷列表导出exel
	 */
	public String downloadexel(String groupid,String where){
		JSONObject jsonobject = JSONObject.parseObject(where);			
		String sql = "select * from ea_defectlib_v";
		if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3")){
			sql = "select * from ea_defectlib_v where groupid = '"+groupid+"'";
			if(!where.equals("null")){
				sql = "select * from ea_defectlib_v where groupid = '"+groupid+"' and "+jsonobject.getString("where");
			}
		}else{
			if(!where.equals("null")){
				sql = "select * from ea_defectlib_v where "+jsonobject.getString("where");
			}			
		}
		String path = "";
		List<DefectLibEntity> list = getBySql(sql,DefectLibEntity.class);
		if(list.size()==0)return "暂无数据";
		ExcelUtil<DefectLibEntity> ex = new ExcelUtil<DefectLibEntity>();
		Map<Integer,String> cells = new HashMap<>();
		Map<Integer,String> cells2 = new HashMap<>();
		cells.put(1, "线路名称");cells2.put(1, "route");
		cells.put(2, "设备类型");cells2.put(2, "devicetype");
		cells.put(3, "设备部件");cells2.put(3, "unit");
		cells.put(4, "设备名称");cells2.put(4, "devicename");
		cells.put(5, "设备等级");cells2.put(5, "grade");
		cells.put(6, "维护班组");cells2.put(6, "groupname");
		cells.put(7, "发现日期");cells2.put(7, "finddate");
		cells.put(8, "发现人员");cells2.put(8, "findpeople");
		cells.put(9, "缺陷部位");cells2.put(9, "defectplace");
		cells.put(10, "缺陷类型");cells2.put(10, "defecttype");
		cells.put(11, "缺陷程度");cells2.put(11, "defectlevel");
		cells.put(12, "缺陷等级");cells2.put(12, "defectscale");
		cells.put(13, "状态量评分");cells2.put(13, "score");
		cells.put(14, "设备状态");cells2.put(14, "devicestatus");
		cells.put(15, "消缺日期");cells2.put(15, "solvedate");
		cells.put(16, "消缺人员");cells2.put(16, "solvepeople");
		cells.put(17, "检修类别");cells2.put(17, "examinetype");
		cells.put(18, "检修方式");cells2.put(18, "examinemode");
		try {
			String date = dateFormat.format(new Date()); 
			String filename = "缺陷信息"+date+".xls";
			path = FILEPATH + "\\export\\"+filename;
			File targetFile = new File(path);
			OutputStream out = new FileOutputStream(targetFile);
			ex.export("缺陷列表",cells,cells2,list, "yyyy-MM-dd", out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path.replace(FILEPATH, "static");
	}
}

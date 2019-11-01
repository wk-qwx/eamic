package com.qwx.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.service.DefectService;
import com.qwx.util.ExcelUtil;

/**
 * 缺陷信息服务
 * @author kal02
 *
 */
@Service
public class DefectServiceImpl extends BaseService<DefectEntity> implements DefectService{

	public DefectServiceImpl() {
		tableName = "ea_defect";
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
	
	/**
	 * 提交缺陷信息
	 */
	public String submit(DefectEntity entity){
		String path = "";
		if(!entity.getPhoto1().equals("") && entity.getPhoto1()!=null){//缺陷部位局部照片
			path = ImgUpload(entity.getPhoto1());//照片上传
			entity.setPhoto1(path);//将照片的存放路径写入
		}
		if(!entity.getPhoto2().equals("") && entity.getPhoto1()!=null){//设备运行编码照片
			path = ImgUpload(entity.getPhoto2());//照片上传
			entity.setPhoto2(path);//将照片的存放路径写入
		}
		if(!entity.getPhoto3().equals("") && entity.getPhoto1()!=null){//设备整体照片
			path = ImgUpload(entity.getPhoto3());//照片上传
			entity.setPhoto3(path);//将照片的存放路径写入
		}
		return add(entity);
	}
	/**
	 * 缺陷列表导出exel
	 */
	public String downloadexel(){
		String sql = "select * from ea_defect";
		List<DefectEntity> list = getBySql(sql,DefectEntity.class);
		ExcelUtil<DefectEntity> ex = new ExcelUtil<DefectEntity>();
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
			File targetFile = new File("D:\\upload\\export\\"+filename);
			OutputStream out = new FileOutputStream(targetFile);
			ex.export("缺陷列表",cells,cells2,list, "yyyy-MM-dd", out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 图片上传
	 * @param photobase64
	 * @param guid
	 * @param photoname
	 * @return 存放路径
	 */
	public String ImgUpload(String photobase64) {
		//图片存放路径
		String path;
		String photoname = UUID.randomUUID().toString().replaceAll("-","")+".jpg";
		//图片存放路径			
		path = getPath();
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		File targetFile = null;		
		OutputStream out = null;
		byte[] b = null;
		
		Base64.Decoder decoder = Base64.getDecoder();
		b = decoder.decode(replaceEnter(photobase64));//base64字节码转文件	
		try {
			targetFile = new File(file,photoname);
			out = new FileOutputStream(targetFile);
			out.write(b);

		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			out.flush();
			out.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return path +"\\"+photoname;
	}
	
	/**
	 * 拼接存放路径
	 * @return path
	 */
	private String getPath(){
		String date = dateFormat.format(new Date()); 
		try {
			String str = PHOTOPATH+"\\image\\"+date;
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("输入的路径不正确。");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String replaceEnter(String str){
        String reg ="[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }	
	
}

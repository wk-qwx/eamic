package com.qwx.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.service.DefectService;

import net.sf.json.JSONArray;

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
	/**
	 * 获取缺陷信息列表
	 */
	public PageList<DefectEntity> getDefects() {
		try{
			String sql="select * from ea_defect";
			PageList<DefectEntity> list = getPageBySql("1","15",sql);
			return list;
			
		}catch(Exception e){
		
		}
		return null;
	}
	/**
	 * 通过id获取缺陷信息
	 */
	public String getCurrentScore(String jsonstr) {
		try{
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			String routeid = jsonobject.getString("routeid");	
			String sql = "select * from ea_defect where routeid='"+routeid+"' and flag = '0'";
			List<DefectEntity> list = getBySql(sql);
			int score = 0;
			for(int i = 0; i < list.size(); i++){
				score += Integer.parseInt(list.get(i).getScore());				
			}
			return String.valueOf(score);
		}catch(Exception e){
		
		}
		return "null";
	}
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
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		try {
			String str = PHOTOPATH+"\\"+date;
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

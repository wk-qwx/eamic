package com.qwx.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qwx.bean.PageList;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.service.DefectService;

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

	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
	/**
	 * 缺陷信息筛选查询分页列表
	 */
	public String getDefectsByFilter(String pageIndex, String pageSize, String groupid, String where){
		String sql = "";
		if(where.equals("null")){
			if(groupid.equals("0")){
				sql = "select * from ea_defect";
			}else{				
				sql = "select * from ea_defect where groupid ='"+groupid+"'";
			}
		}else{
			JSONObject jsonobject = JSONObject.parseObject(where);
			if(groupid.equals("0")){
				sql = "select * from ea_defect where "+jsonobject.getString("where");
			}else{				
				sql = "select * from ea_defect where groupid ='"+groupid+"' and "+jsonobject.getString("where");
			}
		}
		try{
			//返回分页列表
			PageList<DefectEntity> pagelist = getPageBySql(pageIndex,pageSize,sql);
			List<String> result = IsOverdueFun(pagelist);//是否超期
			
			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put("DataSource",result.toString());
			resultMap.put("PageInfo",JSONObject.toJSONString(pagelist.getPageInfo()));//加上分页信息
			return resultMap.toString().replace("\\","");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
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
	/**
	 * 算日期差值
	 * @param dateStart
	 * @param dateStop
	 * @return 差值
	 */
	public String sumDate(String dateStart,String dateStop){

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = dateFormat.parse(dateStart);
            d2 = dateFormat.parse(dateStop);

            //毫秒ms
            long diff = d2.getTime() - d1.getTime();

            /*long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;*/
            long diffDays = diff / (24 * 60 * 60 * 1000);

           //指定小数点后面的位数（2位）
           //double result = (d1 / d2) * 100;
           BigDecimal bd = new BigDecimal(diff);
           double cycleRate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
           return String.valueOf(diffDays);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
	}
	/**
	 * 要返回的结果集中加上是否超期
	 * @param pagelist 分页列表
	 * @return 是否超期的列表
	 */
	public List<String> IsOverdueFun(PageList<DefectEntity> pagelist){		
		//是否超期状态
		String istime = "";
		HashMap<String,Object > reHashMap=new HashMap<String, Object>(); 
		List<String> list2 = new ArrayList<>();
		List<DefectEntity> list = pagelist.getDataSource();
		for(int i = 0; i < list.size(); i++){
			//已消缺1
			if(list.get(i).getFlag().equals("1")){
				istime = "1";
			}else{
				//计算日期差
				istime = sumDate(dateFormat.format(now).toString(),list.get(i).getExaminedate());	
				if(Integer.parseInt(istime)>0)istime = "0";//未消缺已超期 >0
				if(Integer.parseInt(istime)<0)istime = "-1";//未消缺未超期 <0
			}
			String jsonstr = JSONObject.toJSONString(list.get(i),SerializerFeature.WriteMapNullValue);
			JSONObject jsonobject = JSONObject.parseObject(jsonstr);
			Iterator<String> it =  jsonobject.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next();
				reHashMap.put(key, jsonobject.get(key));
	        }
			reHashMap.put("istime", istime);//添加超期状态
			jsonstr = JSONObject.toJSONString(reHashMap,SerializerFeature.WriteMapNullValue);//转化为json格式  
			if (jsonstr != null)
			list2.add(i,jsonstr);
			
		}
		return list2;
	}
}

package com.qwx.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.service.DefectService;
import com.qwx.util.FileUtil;

/**
 * 缺陷信息服务
 * @author kal02
 *
 */
@Service
public class DefectServiceImpl extends BaseService<DefectEntity> implements DefectService{
	@Resource
	BasePagingAndSortingRepository<DefectEntity, String> DefectDao;
	public DefectServiceImpl() {
		tableName = "ea_defect";
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
	
	/**
	 * 提交缺陷信息
	 */
	public String submit(DefectEntity entity){
		String path = "";
		if(!entity.getLocalimg1().equals("") && entity.getLocalimg1()!=null){//缺陷部位局部照片1
			if(entity.getLocalimg1().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getLocalimg1());//照片上传
				entity.setLocalimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getLocalimg2().equals("") && entity.getLocalimg2()!=null){//缺陷部位局部照片2
			if(entity.getLocalimg2().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getLocalimg2());//照片上传
				entity.setLocalimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getLocalimg3().equals("") && entity.getLocalimg3()!=null){//缺陷部位局部照片3
			if(entity.getLocalimg3().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getLocalimg3());//照片上传
				entity.setLocalimg3(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg1().equals("") && entity.getCodeimg1()!=null){//设备运行编码照片1
			if(entity.getCodeimg1().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getCodeimg1());//照片上传
				entity.setCodeimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg2().equals("") && entity.getCodeimg2()!=null){//设备运行编码照片2
			if(entity.getCodeimg2().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getCodeimg2());//照片上传
				entity.setCodeimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg3().equals("") && entity.getCodeimg3()!=null){//设备运行编码照片3
			if(entity.getCodeimg3().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getCodeimg3());//照片上传
				entity.setCodeimg3(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg1().equals("") && entity.getWholeimg1()!=null){//设备整体照片1
			if(entity.getWholeimg1().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getWholeimg1());//照片上传
				entity.setWholeimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg2().equals("") && entity.getWholeimg2()!=null){//设备整体照片2
			if(entity.getWholeimg2().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getWholeimg2());//照片上传
				entity.setWholeimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg3().equals("") && entity.getWholeimg3()!=null){//设备整体照片3
			if(entity.getWholeimg3().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getWholeimg3());//照片上传
				entity.setWholeimg3(path);//将照片的存放路径写入
			}
		}
		return add(entity);
	}
	/**
	 * 修改缺陷信息
	 */
	public String updatex(DefectEntity entity){
		DefectEntity row = getById(entity.getId());
		String path = "";
		
		if(!entity.getLocalimg1().equals("") && entity.getLocalimg1()!=null){//缺陷部位局部照片1
			if(entity.getLocalimg1().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getLocalimg1(),entity.getLocalimg1());//照片上传
				entity.setLocalimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getLocalimg2().equals("") && entity.getLocalimg2()!=null){//缺陷部位局部照片2
			if(entity.getLocalimg2().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getLocalimg2(),entity.getLocalimg2());//照片上传
				entity.setLocalimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getLocalimg3().equals("") && entity.getLocalimg3()!=null){//缺陷部位局部照片3
			if(entity.getLocalimg3().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getLocalimg3(),entity.getLocalimg3());//照片上传
				entity.setLocalimg3(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg1().equals("") && entity.getCodeimg1()!=null){//设备运行编码照片1
			if(entity.getCodeimg1().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getCodeimg1(),entity.getCodeimg1());//照片上传
				entity.setCodeimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg2().equals("") && entity.getCodeimg2()!=null){//设备运行编码照片2
			if(entity.getCodeimg2().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getCodeimg2(),entity.getCodeimg2());//照片上传
				entity.setCodeimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getCodeimg3().equals("") && entity.getCodeimg3()!=null){//设备运行编码照片3
			if(entity.getCodeimg3().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getCodeimg3(),entity.getCodeimg3());//照片上传
				entity.setCodeimg3(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg1().equals("") && entity.getWholeimg1()!=null){//设备整体照片1
			if(entity.getWholeimg1().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getWholeimg1(),entity.getWholeimg1());//照片上传
				entity.setWholeimg1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg2().equals("") && entity.getWholeimg2()!=null){//设备整体照片2
			if(entity.getWholeimg2().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getWholeimg2(),entity.getWholeimg2());//照片上传
				entity.setWholeimg2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getWholeimg3().equals("") && entity.getWholeimg3()!=null){//设备整体照片3
			if(entity.getWholeimg3().indexOf("D:\\upload")==-1){				
				path = photoEx(row.getWholeimg3(),entity.getWholeimg3());//照片上传
				entity.setWholeimg3(path);//将照片的存放路径写入
			}
		}
		return update(entity);
	}
	
	/**
	 * 缺陷信息删除
	 */
	public String delDefect(String id,String groupid){
		if(!groupid.equals("0"))return "暂无操作权限";
		
		try {
			DefectEntity row = DefectDao.findOne(id);
			//根据id删除		
			DefectDao.delete(id);
			//删除服务器的上传文件
			if(!row.getLocalimg1().equals("") && row.getLocalimg1()!=null){
				FileUtil.deleteFie(row.getLocalimg1());
			}
			if(!row.getCodeimg1().equals("") && row.getCodeimg1()!=null){
				FileUtil.deleteFie(row.getCodeimg1());
			}
			if(!row.getWholeimg1().equals("") && row.getWholeimg1()!=null){
				FileUtil.deleteFie(row.getWholeimg1());
			}
			if(!row.getLocalimg2().equals("") && row.getLocalimg2()!=null){
				FileUtil.deleteFie(row.getLocalimg2());
			}
			if(!row.getCodeimg2().equals("") && row.getCodeimg2()!=null){
				FileUtil.deleteFie(row.getCodeimg2());
			}
			if(!row.getWholeimg2().equals("") && row.getWholeimg2()!=null){
				FileUtil.deleteFie(row.getWholeimg2());
			}
			if(!row.getLocalimg3().equals("") && row.getLocalimg3()!=null){
				FileUtil.deleteFie(row.getLocalimg3());
			}
			if(!row.getCodeimg3().equals("") && row.getCodeimg3()!=null){
				FileUtil.deleteFie(row.getCodeimg3());
			}
			if(!row.getWholeimg3().equals("") && row.getWholeimg3()!=null){
				FileUtil.deleteFie(row.getWholeimg3());
			}
			return "true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除异常！";
		}
				
	}
	/**
	 * base64转图片
	 * @param photobase64 字节码
	 * @return 存放路径
	 */
	public String base64toImg(String photobase64) {
		//图片存放路径			
		String path = getPath();
		String photoname = UUID.randomUUID().toString().replaceAll("-","")+".jpg";//图片名称
		
		try {
			byte[] b = null;		
			Base64.Decoder decoder = Base64.getDecoder();
			b = decoder.decode(replaceEnter(photobase64));//base64字节码转文件
			FileUtil.uploadFile(b,path,photoname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path+photoname;
	}
	/**
	 * 图片编辑时覆盖处理
	 * @param oldPhoto 存放路径
	 * @param newPhoto 字节码
	 * @return 新的存放路径
	 */
	public String photoEx(String oldPhoto,String newPhoto) {
		
		//图片存放路径			
		String path = getPath();
		String photoname = UUID.randomUUID().toString().replaceAll("-","")+".jpg";//图片名称
		
		try {
			byte[] b = null;		
			Base64.Decoder decoder = Base64.getDecoder();
			b = decoder.decode(replaceEnter(newPhoto));//base64字节码转文件
			FileUtil.uploadFile(b,path,photoname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判断该路径下的文件是否存在，如果存在就删除
		FileUtil.deleteFie(oldPhoto);
		return path+photoname;
	}
	/**
	 * 拼接存放路径
	 * @return path
	 */
	private String getPath(){
		String date = dateFormat.format(new Date()); 
		try {			
			String str = PHOTOPATH+"\\image\\"+date+"\\";
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("输入的路径不正确。");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 验证base64
	 * @param str
	 * @return
	 */
	public static String replaceEnter(String str){
        String reg ="[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }	
	
}

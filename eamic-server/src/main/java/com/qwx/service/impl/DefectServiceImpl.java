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
		if(!entity.getPhoto1().equals("") && entity.getPhoto1()!=null){//缺陷部位局部照片
			if(entity.getPhoto1().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getPhoto1());//照片上传
				entity.setPhoto1(path);//将照片的存放路径写入
			}
		}
		if(!entity.getPhoto2().equals("") && entity.getPhoto1()!=null){//设备运行编码照片
			if(entity.getPhoto2().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getPhoto2());//照片上传
				entity.setPhoto2(path);//将照片的存放路径写入
			}
		}
		if(!entity.getPhoto3().equals("") && entity.getPhoto1()!=null){//设备整体照片
			if(entity.getPhoto3().indexOf("D:\\upload")==-1){				
				path = base64toImg(entity.getPhoto3());//照片上传
				entity.setPhoto3(path);//将照片的存放路径写入
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
		if(!entity.getPhoto1().equals("") && entity.getPhoto1()!=null){//缺陷部位局部照片
			if(entity.getPhoto1().indexOf("D:\\upload")==-1){				
				//图片覆盖
				path = photoEx(row.getPhoto1(),entity.getPhoto1());
				entity.setPhoto1(path);
			}
		}
		if(!entity.getPhoto2().equals("") && entity.getPhoto1()!=null){//设备运行编码照片
			if(entity.getPhoto2().indexOf("D:\\upload")==-1){				
				//图片覆盖
				path = photoEx(row.getPhoto2(),entity.getPhoto2());
				entity.setPhoto2(path);
			}
		}
		if(!entity.getPhoto3().equals("") && entity.getPhoto1()!=null){//设备整体照片
			if(entity.getPhoto3().indexOf("D:\\upload")==-1){				
				//图片覆盖
				path = photoEx(row.getPhoto3(),entity.getPhoto3());
				entity.setPhoto3(path);
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
			//删除服务器上传文件
			if(!row.getPhoto1().equals("") && row.getPhoto1()!=null){
				FileUtil.deleteFie(row.getPhoto1());
			}
			if(!row.getPhoto2().equals("") && row.getPhoto2()!=null){
				FileUtil.deleteFie(row.getPhoto2());
			}
			if(!row.getPhoto3().equals("") && row.getPhoto3()!=null){
				FileUtil.deleteFie(row.getPhoto3());
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

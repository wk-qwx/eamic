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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.DefectEntity;
import com.qwx.entity.RecordEntity;
import com.qwx.service.DefectService;
import com.qwx.util.ConfigUtil;
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
	@Resource
	BasePagingAndSortingRepository<RecordEntity, String> RecordDao;
	public DefectServiceImpl() {
		tableName = "ea_defect";
	}
	//照片存放路径
	public static final String PHOTOPATH = ConfigUtil.getProperty("filePath");
	//缺陷删除密码
	public static final String DELKEY = ConfigUtil.getProperty("delkey");
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
	public String updatex(DefectEntity entity, String userid){
		DefectEntity row = getById(entity.getId());
		String path = "";
		
		if(!entity.getLocalimg1().equals("") && entity.getLocalimg1()!=null){//缺陷部位局部照片1		
			if(entity.getLocalimg1().indexOf(":\\upload")==-1){
				path = photoEx(row.getLocalimg1(),entity.getLocalimg1());//照片上传
				entity.setLocalimg1(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getLocalimg1());
		
		if(!entity.getLocalimg2().equals("") && entity.getLocalimg2()!=null){//缺陷部位局部照片2
			if(entity.getLocalimg2().indexOf(":\\upload")==-1){
				path = photoEx(row.getLocalimg2(),entity.getLocalimg2());//照片上传
				entity.setLocalimg2(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getLocalimg2());
		
		if(!entity.getLocalimg3().equals("") && entity.getLocalimg3()!=null){//缺陷部位局部照片3
			if(entity.getLocalimg3().indexOf(":\\upload")==-1){
				path = photoEx(row.getLocalimg3(),entity.getLocalimg3());//照片上传
				entity.setLocalimg3(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getLocalimg3());
		
		if(!entity.getCodeimg1().equals("") && entity.getCodeimg1()!=null){//设备运行编码照片1
			if(entity.getCodeimg1().indexOf(":\\upload")==-1){
				path = photoEx(row.getCodeimg1(),entity.getCodeimg1());//照片上传
				entity.setCodeimg1(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getCodeimg1());
		
		if(!entity.getCodeimg2().equals("") && entity.getCodeimg2()!=null){//设备运行编码照片2
			if(entity.getCodeimg2().indexOf(":\\upload")==-1){
				path = photoEx(row.getCodeimg2(),entity.getCodeimg2());//照片上传
				entity.setCodeimg2(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getCodeimg2());
		
		if(!entity.getCodeimg3().equals("") && entity.getCodeimg3()!=null){//设备运行编码照片3		
			if(entity.getCodeimg3().indexOf(":\\upload")==-1){
				path = photoEx(row.getCodeimg3(),entity.getCodeimg3());//照片上传
				entity.setCodeimg3(path);//将照片的存放路径写入	
			}
		}else FileUtil.deleteFie(row.getCodeimg3());
		
		if(!entity.getWholeimg1().equals("") && entity.getWholeimg1()!=null){//设备整体照片1
			if(entity.getWholeimg1().indexOf(":\\upload")==-1){	
				path = photoEx(row.getWholeimg1(),entity.getWholeimg1());//照片上传
				entity.setWholeimg1(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getWholeimg1());
		
		if(!entity.getWholeimg2().equals("") && entity.getWholeimg2()!=null){//设备整体照片2		
			if(entity.getWholeimg2().indexOf(":\\upload")==-1){
				path = photoEx(row.getWholeimg2(),entity.getWholeimg2());//照片上传
				entity.setWholeimg2(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getWholeimg2());
		
		if(!entity.getWholeimg3().equals("") && entity.getWholeimg3()!=null){//设备整体照片3		
			if(entity.getWholeimg3().indexOf(":\\upload")==-1){
				path = photoEx(row.getWholeimg3(),entity.getWholeimg3());//照片上传
				entity.setWholeimg3(path);//将照片的存放路径写入
			}
		}else FileUtil.deleteFie(row.getWholeimg3());
		//插入操作记录
		recordAdd(row.getId(), "编辑", userid);
		return update(entity);
	}
	
	/**
	 * 缺陷信息删除
	 */	
	public String delDefect(String id, String password, String userid){
		if(!DELKEY.equals(password))return "密码错误";
		try {
			DefectEntity row = DefectDao.findOne(id);
			//删除		
			//delete(row);
			//删除服务器的上传文件
			/*FileUtil.deleteFie(row.getLocalimg1());
			FileUtil.deleteFie(row.getCodeimg1());
			FileUtil.deleteFie(row.getWholeimg1());
			FileUtil.deleteFie(row.getLocalimg2());
			FileUtil.deleteFie(row.getCodeimg2());
			FileUtil.deleteFie(row.getWholeimg2());
			FileUtil.deleteFie(row.getLocalimg3());
			FileUtil.deleteFie(row.getCodeimg3());
			FileUtil.deleteFie(row.getWholeimg3());*/
			//更新数据为已被删除状态
			row.setFlag("-1");
			update(row);
			//插入操作记录
			recordAdd(row.getId(), "删除", userid);
			return "true";
		} catch (Exception e) {			
			// TODO Auto-generated catch block			
			e.printStackTrace();
			return "删除异常！";
		}
				
	}
	/**
	 * 插入操作记录
	 * @param defectid 缺陷id
	 * @param opertype 操作类型
	 * @param userid 用户id
	 */
	public void recordAdd(String defectid, String opertype, String userid){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			RecordEntity entity = new RecordEntity();
			entity.setDefectid(defectid);
			entity.setOpertype(opertype);
			entity.setUserid(userid);
			entity.setOpertime(df.format(new Date()));
			entity.setId(UUID.randomUUID().toString());
			RecordDao.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			String str = PHOTOPATH + "\\image\\"+date+"\\";
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

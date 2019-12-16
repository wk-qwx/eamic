package com.qwx.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.ReadMaterialEntity;
import com.qwx.service.ReadMaterialService;
import com.qwx.util.ConfigUtil;
import com.qwx.util.FileUtil;

/**
 * 参考资料服务
 * @author kal02
 *
 */
@Service
public class ReadMaterialServiceImpl extends BaseService<ReadMaterialEntity> implements ReadMaterialService{
	@Resource
	BasePagingAndSortingRepository<ReadMaterialEntity, String> ReadMaterialDao;
	public ReadMaterialServiceImpl() {
		tableName = "ea_readmaterial";
	}	
	//文件存放路径
	public static final String FILEPATH = ConfigUtil.getProperty("filePath");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
	/**
	 * 文件上传
	 */
	public String upload(CommonsMultipartFile file) {
		try {
			String filename = file.getOriginalFilename();
			String uploadpath = FILEPATH+"\\material\\";
			File dir = new File(uploadpath);
			if(!dir.exists())
				dir.mkdir();
			ReadMaterialEntity row = new ReadMaterialEntity();
			row.setId(UUID.randomUUID().toString());
			row.setFilename(filename.substring(0,filename.lastIndexOf(".")));
			row.setFiletype(filename.substring(filename.lastIndexOf(".") + 1));
			row.setUploaddate(dateFormat.format(new Date()));
			row.setUploadpath(uploadpath+FileUtil.renameToUUID(filename));			
			
			long startTime=System.currentTimeMillis();
			
			File newFile=new File(row.getUploadpath());
			//通过CommonsMultipartFile的方法直接写文件
			file.transferTo(newFile);
			
			long endTime=System.currentTimeMillis();
			System.out.println("上传时间："+String.valueOf(endTime-startTime)+"ms");
			ReadMaterialDao.save(row);
			return "上传成功！";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "上传异常！";
		}
	}
	/**
	 * 参考资料删除
	 */
	public String delMaterial(String id,String groupid){
		if(groupid.equals("1")||groupid.equals("2")||groupid.equals("3"))return "暂无操作权限";
		
		try {
			ReadMaterialEntity row = ReadMaterialDao.findOne(id);
			//数据库行删除	
			ReadMaterialDao.delete(row);
			//删除服务器上传文件
			FileUtil.deleteFie(row.getUploadpath());
			return "true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除异常！";
		}
	}
}

package com.qwx.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qwx.database.BaseService;
import com.qwx.entity.ReadMaterialEntity;
import com.qwx.service.ReadMaterialService;
import com.qwx.util.FileUtil;
import com.qwx.util.StringUtil;

/**
 * 参考资料服务
 * @author kal02
 *
 */
@Service
public class ReadMaterialServiceImpl extends BaseService<ReadMaterialEntity> implements ReadMaterialService{


	public ReadMaterialServiceImpl() {
		tableName = "ea_readmaterial";
	}	
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
			if(add(row)!="")
				return "上传成功！";
			else
				return "上传失败！";
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "上传异常！";
		}
	}
	/**
	 * 文件下载
	 */
	public List<ReadMaterialEntity> download(String jsonstr) {
		
		return null;
	}
}

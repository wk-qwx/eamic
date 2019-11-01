package com.qwx.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.qwx.entity.ReadMaterialEntity;
import com.qwx.util.ConfigUtil;

/**
 * 参考资料信息接口
 * @author kal02
 *
 */
public interface ReadMaterialService {
	//文件存放路径
	public static String FILEPATH = ConfigUtil.getProperty("filePath");
	/**
	 * 参考资料上传
	 * @param 文件流
	 * @return
	 */
	public String upload(CommonsMultipartFile file);
	/**
	 * 参考资料下载
	 * @param jsonstr
	 * @return
	 */
	public List<ReadMaterialEntity> download(String jsonstr);
}

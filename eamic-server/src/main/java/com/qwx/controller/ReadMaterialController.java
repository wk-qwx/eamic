package com.qwx.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.ReadMaterialEntity;
import com.qwx.service.ReadMaterialService;


@RestController
@RequestMapping(value = "/readmaterial")
public class ReadMaterialController extends BaseController<ReadMaterialEntity> {

	
	public ReadMaterialController() {
		tableName = "ea_readmaterial";
	}

	@Resource
	ReadMaterialService readMaterialService;

	/**
	 * 文件上传
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public HttpResponse<String> upload(@RequestParam("file") CommonsMultipartFile file) {
		try {			
			
			return new HttpResponse<String>(readMaterialService.upload(file));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 参考资料删除
	 */
	@RequestMapping(value = "/delMaterial", method = RequestMethod.POST)
	public HttpResponse<String> delMaterial(@RequestParam("id") String id,HttpServletRequest request) {
		try {			
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			System.out.println("登录用户分组id："+groupid);
			if(groupid == null)groupid = "0";
			return new HttpResponse<String>(readMaterialService.delMaterial(id,groupid));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
}

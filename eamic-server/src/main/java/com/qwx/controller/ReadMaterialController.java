package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponseList;
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
	 * 文件下载
	 */
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public HttpResponseList<ReadMaterialEntity> download(@RequestBody String jsonstr) {
		try {			
			
			return new HttpResponseList<ReadMaterialEntity>(null);
		} catch (Exception e) {
			return new HttpResponseList<ReadMaterialEntity>(ResponseStatusCode.C400);
		}
	}	
}

package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.DefectEntity;
import com.qwx.service.DefectService;


@RestController
@RequestMapping(value = "/defect")
public class DefectController extends BaseController<DefectEntity> {

	
	public DefectController() {
		tableName = "ea_defect";
	}

	@Resource
	DefectService defectService;
		
	/**
	 * 提交缺陷信息
	 * @param entity
	 * @return 缺陷信息id
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public HttpResponse<String> submit(@RequestBody DefectEntity entity) {
		try {			
			return new HttpResponse<String>(defectService.submit(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 修改缺陷信息
	 * @param entity
	 * @return 缺陷信息id
	 */
	@RequestMapping(value = "/updatex", method = RequestMethod.POST)
	public HttpResponse<String> updatex(@RequestBody DefectEntity entity) {
		try {			
			return new HttpResponse<String>(defectService.updatex(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
	
	/**
	 * 缺陷删除
	 */
	@RequestMapping(value = "/delDefect", method = RequestMethod.POST)
	@Transactional(rollbackFor=Exception.class)
	public HttpResponse<String> delDefect(@RequestParam("id") String id) {
		try {			
			
			return new HttpResponse<String>(defectService.delDefect(id));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

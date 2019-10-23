package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponsePageList;
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
	 * 读取分页缺陷列表
	 */
	@RequestMapping(value = "/getDefects", method = RequestMethod.POST)
	public HttpResponsePageList<DefectEntity> getDefects() {
		try {			
			return new HttpResponsePageList<DefectEntity>(defectService.getDefects());
		} catch (Exception e) {
			return new HttpResponsePageList<DefectEntity>(ResponseStatusCode.C400);
		}
	}	
	
	/**
	 * 通过id获取缺陷信息
	 * @return 未消缺状态量扣分总数
	 */
	@RequestMapping(value = "/getCurrentScore", method = RequestMethod.POST)
	public HttpResponse<String>  getCurrentScore(@RequestBody String jsonstr) {
		try {			
			return new HttpResponse<String>(defectService.getCurrentScore(jsonstr));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
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
}

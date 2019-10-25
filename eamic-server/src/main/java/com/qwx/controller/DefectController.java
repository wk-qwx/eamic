package com.qwx.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * 缺陷查询列表分页
	 * @param where where 条件拼接字符
	 * @return 缺陷列表
	 */
	@RequestMapping(value = "/getDefectsByFilter/{pageIndex}/{pageSize}", method = RequestMethod.POST)
	public HttpResponse<String> getDefectsByFilter(@RequestBody String where,HttpServletRequest request,
										   @PathVariable("pageIndex") String pageIndex,
										   @PathVariable("pageSize") String pageSize) {
		try {			
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			System.out.println("登录用户分组id："+groupid);
			if(groupid == null)groupid = "0";
			return new HttpResponse<String>(defectService.getDefectsByFilter(pageIndex,pageSize,groupid,where));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	
}

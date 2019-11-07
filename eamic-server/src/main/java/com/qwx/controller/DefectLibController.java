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
import com.qwx.entity.DefectLibEntity;
import com.qwx.service.DefectLibService;


@RestController
@RequestMapping(value = "/defectlib")
public class DefectLibController extends BaseController<DefectLibEntity> {

	
	public DefectLibController() {
		tableName = "ea_defectlib_v";
	}

	@Resource
	DefectLibService defectlibService;
		
	
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
			return new HttpResponse<String>(defectlibService.getDefectsByFilter(pageIndex,pageSize,groupid,where));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	/**
	 * 缺陷列表exel导出
	 */
	@RequestMapping(value = "/downloadexel", method = RequestMethod.POST)
	public HttpResponse<String> downloadexel(@RequestBody String where,HttpServletRequest request) {
		try {	
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			System.out.println("登录用户分组id："+groupid);
			if(groupid == null)groupid = "0";
			return new HttpResponse<String>(defectlibService.downloadexel(groupid,where));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

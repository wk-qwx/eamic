package com.qwx.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.bean.HttpResponseList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.RoutelibEntity;
import com.qwx.service.RoutelibService;


@RestController
@RequestMapping(value = "/routelib")
public class RoutelibController extends BaseController<RoutelibEntity> {

	
	public RoutelibController() {
		tableName = "ea_routelib";
	}

	@Resource
	RoutelibService routelibService;

	/**
	 * 读取电站分页列表
	 */
	@RequestMapping(value = "/getRoutes", method = RequestMethod.POST)
	public HttpResponseList<RoutelibEntity> getStations(@RequestBody String jsonstr,HttpServletRequest request) {
		try {	
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			System.out.println("登录用户分组id："+groupid);
			if(groupid == null)groupid = "0";
			return new HttpResponseList<RoutelibEntity>(routelibService.getRoutes(jsonstr,groupid));
		} catch (Exception e) {
			return new HttpResponseList<RoutelibEntity>(ResponseStatusCode.C400);
		}
	}	
	
	/**
	 * 根据线路名过滤
	 */
	@RequestMapping(value = "/getRouteByName", method = RequestMethod.POST)
	public HttpResponseList<RoutelibEntity> getRouteByName(@RequestBody String jsonstr,HttpServletRequest request) {
		try {		
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			if(groupid == null)groupid = "0";
			return new HttpResponseList<RoutelibEntity>(routelibService.getRouteByName(jsonstr,groupid));
		} catch (Exception e) {
			return new HttpResponseList<RoutelibEntity>(ResponseStatusCode.C400);
		}
	}	
	/*
	 * 设备状态查询
	 
	@RequestMapping(value = "/getRoutesOfState/{pageIndex}/{pageSize}", method = RequestMethod.POST)
	public HttpResponse<String> getRoutesOfState(@RequestBody String jsonstr,HttpServletRequest request,
											     @PathVariable("pageIndex") String pageIndex,
											     @PathVariable("pageSize") String pageSize) {
		try {		
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			if(groupid == null)groupid = "0";
			return new HttpResponse<String>(routelibService.getRoutesOfState(jsonstr,groupid,pageIndex,pageSize));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	*/
	
}

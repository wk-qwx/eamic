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
import com.qwx.entity.RouteStateEntity;
import com.qwx.service.RouteStateService;


@RestController
@RequestMapping(value = "/routestate")
public class RouteStateController extends BaseController<RouteStateEntity> {

	
	public RouteStateController() {
		tableName = "ea_routestate_v";
	}

	@Resource
	RouteStateService routestateService;

		
	/*
	 * 设备状态查询
	 */
	@RequestMapping(value = "/getRoutesOfState/{pageIndex}/{pageSize}", method = RequestMethod.POST)
	public HttpResponse<String> getRoutesOfState(@RequestBody String jsonstr,HttpServletRequest request,
											     @PathVariable("pageIndex") String pageIndex,
											     @PathVariable("pageSize") String pageSize) {
		try {		
			Enumeration headerNames = request.getHeaderNames();
			String groupid = request.getHeader("groupid");
			if(groupid == null)groupid = "0";
			return new HttpResponse<String>(routestateService.getRoutesOfState(jsonstr,groupid,pageIndex,pageSize));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	
	
}

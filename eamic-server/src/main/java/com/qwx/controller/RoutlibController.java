package com.qwx.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.RoutelibEntity;
import com.qwx.service.RoutelibService;


@RestController
@RequestMapping(value = "/routelib")
public class RoutlibController extends BaseController<RoutelibEntity> {

	
	public RoutlibController() {
		tableName = "ea_routelib";
	}

	@Resource
	RoutelibService routelibService;

	/**
	 * 读取电站分页列表
	 */
	@RequestMapping(value = "/getStations", method = RequestMethod.POST)
	public HttpResponsePageList<RoutelibEntity> getStations(@RequestBody String jsonstr) {
		try {			
			return new HttpResponsePageList<RoutelibEntity>(routelibService.getStations(jsonstr));
		} catch (Exception e) {
			return new HttpResponsePageList<RoutelibEntity>(ResponseStatusCode.C400);
		}
	}	
	
	/**
	 * 过滤电站
	 */
	@RequestMapping(value = "/getRouteByName", method = RequestMethod.POST)
	public HttpResponsePageList<RoutelibEntity> getRouteByName(@RequestBody String jsonstr) {
		try {			
			return new HttpResponsePageList<RoutelibEntity>(routelibService.getRouteByName(jsonstr));
		} catch (Exception e) {
			return new HttpResponsePageList<RoutelibEntity>(ResponseStatusCode.C400);
		}
	}	

	
}

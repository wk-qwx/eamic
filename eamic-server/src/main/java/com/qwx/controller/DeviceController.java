package com.qwx.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.DeviceEntity;
import com.qwx.service.DeviceService;


@RestController
@RequestMapping(value = "/device")
public class DeviceController extends BaseController<DeviceEntity> {

	
	public DeviceController() {
		tableName = "ea_device";
	}

	@Resource
	DeviceService deviceService;

	/**
	 * 读取设备列表
	 */
	@RequestMapping(value = "/getDevices", method = RequestMethod.GET)
	public HttpResponse<String>  getDevices() {
		try {			
			return new HttpResponse<String>(deviceService.getDevices());
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}	

	
}

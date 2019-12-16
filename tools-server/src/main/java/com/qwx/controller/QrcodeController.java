package com.qwx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.Qrcode2Entity;
import com.qwx.entity.QrcodeEntity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.service.QrcodeService;


@RestController
@RequestMapping(value = "/qrcode")
public class QrcodeController extends BaseController<Qrcode2Entity> {

	
	public QrcodeController() {
		tableName = "tl_qrcode";
	}

	@Resource
	QrcodeService qrcodeService;

	/**
	 * 1.二维码创建
	 * 2.工器具导入
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public HttpResponse<String> submit(@RequestBody QrcodeView2Entity entity) {
		try {			
			
			return new HttpResponse<String>(qrcodeService.submit(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}		
		
	
}

package com.qwx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.service.QrcodeViewService;


@RestController
@RequestMapping(value = "/qrcodev")
public class QrcodeViewController extends BaseController<QrcodeViewEntity> {

	
	public QrcodeViewController() {
		tableName = "qrcode_v";
	}

	@Resource
	QrcodeViewService qrcodeviewService;

	/**
	 * 二维码列表
	 */
	@RequestMapping(value = "/getQrcodeView", method = RequestMethod.GET)
	public HttpResponsePageList<QrcodeView2Entity> getQrcodeView(@RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<QrcodeView2Entity>(qrcodeviewService.getQrcodeView(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<QrcodeView2Entity>(ResponseStatusCode.C400);
		}
	}		
	
	/**
	 * 二维码下载导出
	 */
	@RequestMapping(value = "/exportFile", method = RequestMethod.POST)
	public HttpResponse<String> export(@RequestBody QrcodeView2Entity entity) {
		try {			
			
			return new HttpResponse<String>(qrcodeviewService.export(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}

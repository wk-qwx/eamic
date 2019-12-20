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
import com.qwx.entity.Qrcode2Entity;
import com.qwx.entity.QrcodeEntity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;
import com.qwx.entity.ToolsLibViewEntity;
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
	 * 获取二维码文件列表
	 */
	@RequestMapping(value = "/getQrcodeFileList", method = RequestMethod.GET)
	public HttpResponsePageList<Qrcode2Entity> getQrcodeFileList(@RequestParam("page") String page,@RequestParam("limit") String limit) {
		try {			
			
			return new HttpResponsePageList<Qrcode2Entity>(qrcodeService.getQrcodeFileList(page, limit));
		} catch (Exception e) {
			return new HttpResponsePageList<Qrcode2Entity>(ResponseStatusCode.C400);
		}
	}
	/**
	 * 根据筛选条件获取二维码列表
	 */
	@RequestMapping(value = "/getListByFilter", method = RequestMethod.GET)
	public HttpResponsePageList<Qrcode2Entity> getListByFilter(@RequestParam("page") String page,@RequestParam("limit") String limit, @RequestParam("whereStr") String whereStr) {
		try {			
			
			return new HttpResponsePageList<Qrcode2Entity>(qrcodeService.getListByFilter(page, limit, whereStr));
		} catch (Exception e) {
			return new HttpResponsePageList<Qrcode2Entity>(ResponseStatusCode.C400);
		}
	}
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

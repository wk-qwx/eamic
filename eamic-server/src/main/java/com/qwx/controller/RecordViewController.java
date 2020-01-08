package com.qwx.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.RecordViewEntity;
import com.qwx.service.RecordViewService;


@RestController
@RequestMapping(value = "/recordv")
public class RecordViewController extends BaseController<RecordViewEntity> {

	
	public RecordViewController() {
		tableName = "ea_record_v";
	}

	@Resource
	RecordViewService recordvService;
		
	
	/**
	 * 操作记录列表分页
	 * @param where where 条件拼接字符
	 * @return 缺陷列表
	 */
	@RequestMapping(value = "/getRecordByFilter/{pageIndex}/{pageSize}", method = RequestMethod.POST)
	public HttpResponsePageList<RecordViewEntity> getRecordByFilter(@RequestBody String where,
										   @PathVariable("pageIndex") String pageIndex,
										   @PathVariable("pageSize") String pageSize) {
		try {						
			return new HttpResponsePageList<RecordViewEntity>(recordvService.getRecordByFilter(pageIndex,pageSize,where));
		} catch (Exception e) {
			return new HttpResponsePageList<RecordViewEntity>(ResponseStatusCode.C400);
		}
	}	
	
}

package com.qwx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.bean.HttpResponseList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.controller.BaseController;
import com.qwx.entity.DeductRuleEntity;
import com.qwx.service.DeductRuleService;


@RestController
@RequestMapping(value = "/deduct")
public class DeductRuleController extends BaseController<DeductRuleEntity> {

	
	public DeductRuleController() {
		tableName = "ea_deduct_rule";
	}

	@Resource
	DeductRuleService deductRuleService;

	/**
	 * 评分导则
	 */
	@RequestMapping(value = "/Guideline", method = RequestMethod.POST)
	public HttpResponseList<DeductRuleEntity> Guideline(@RequestBody String jsonstr) {
		try {			
			List<DeductRuleEntity> list = deductRuleService.Guideline(jsonstr);
			return new HttpResponseList<DeductRuleEntity>(list);
		} catch (Exception e) {
			return new HttpResponseList<DeductRuleEntity>(ResponseStatusCode.C400);
		}
	}	
	
	
}

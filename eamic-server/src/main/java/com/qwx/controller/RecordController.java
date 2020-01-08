package com.qwx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qwx.controller.BaseController;
import com.qwx.entity.RoutelibEntity;

@RestController
@RequestMapping(value = "/record")
public class RecordController extends BaseController<RoutelibEntity> {

	
	public RecordController() {
		tableName = "ea_record";
	}

	
}

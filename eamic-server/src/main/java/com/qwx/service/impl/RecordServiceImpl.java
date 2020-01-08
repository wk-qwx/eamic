package com.qwx.service.impl;

import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.RecordEntity;
import com.qwx.service.RecordService;

/**
 * 操作记录信息服务
 * @author kal02
 *
 */
@Service
public class RecordServiceImpl extends BaseService<RecordEntity> implements RecordService{


	public RecordServiceImpl() {
		tableName = "ea_record";
	}	
	
}

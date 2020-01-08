package com.qwx.service;

import com.qwx.bean.PageList;
import com.qwx.entity.RecordViewEntity;

/**
 * 操作记录信息接口
 * @author kal02
 *
 */
public interface RecordViewService {
	
	/**
	 * 操作记录列表分页
	 * @param pageIndex
	 * @param pageSize
	 * @param where
	 * @return 操作记录列表
	 */
	public PageList<RecordViewEntity> getRecordByFilter(String pageIndex, String pageSize, String where);
}

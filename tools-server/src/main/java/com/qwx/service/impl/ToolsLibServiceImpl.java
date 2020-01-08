package com.qwx.service.impl;

import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.ToolsLib2Entity;
import com.qwx.service.ToolsLibService;

/**
 * 工器具管理服务
 * @author kal02
 *
 */
@Service
public class ToolsLibServiceImpl extends BaseService<ToolsLib2Entity> implements ToolsLibService{
	
	public ToolsLibServiceImpl() {
		tableName = "tl_toolslib";
	}
	/**
	 *安全工器具更新
	 */
	public String updatex(ToolsLib2Entity entity) {
		ToolsLib2Entity row = getById(entity.getId());
		if(!row.getDevicestate().equals(entity.getDevicestate()) || !row.getLastcheck().equals(entity.getLastcheck())){
			entity.setIscheck("1");
		}else entity.setIscheck("0");
		return update(entity);
	}
	
}

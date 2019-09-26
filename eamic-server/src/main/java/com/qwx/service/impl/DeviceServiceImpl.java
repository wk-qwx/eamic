package com.qwx.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qwx.database.BaseService;
import com.qwx.entity.DeviceEntity;
import com.qwx.service.DeviceService;

import net.sf.json.JSONArray;

/**
 * 设备信息服务
 * @author kal02
 *
 */
@Service
public class DeviceServiceImpl extends BaseService<DeviceEntity> implements DeviceService{


	public DeviceServiceImpl() {
		tableName = "\"ea_device\"";
	}

	public String getDevices() {
		try{
			String sql="select * from ea_device";
			List<DeviceEntity> userlist = getBySql(sql,DeviceEntity.class);
			String json=JSONArray.fromObject(userlist).toString();
			return json;
		}catch(Exception e){
		
		}
		return "false";
	}
	

}

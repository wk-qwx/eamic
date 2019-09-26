package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 设备信息表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_DEVICE\"")
public class DeviceEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//线路
	@Column(name = "\"ROUTE\"")
	private String route;
	//设备类型
	@Column(name = "\"DEVICETYPE\"")
	private String devicetype;
	//设备名称
	@Column(name = "\"DEVICENAME\"")
	private String devicename;
	//部件
	@Column(name = "\"UNIT\"")
	private String unit;
	//等级
	@Column(name = "\"GRADE\"")
	private String grade;
	//投运时间
	@Column(name = "\"APPLYDATE\"")
	private String applydate;
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	
	
}

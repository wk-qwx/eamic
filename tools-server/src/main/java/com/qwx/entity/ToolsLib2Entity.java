package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qwx.util.Des3Util;

/**
 * 设备工具表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"TL_TOOLSLIB\"")
public class ToolsLib2Entity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//编码
	@Column(name = "\"QRCODE\"")
	private String qrcode;
	//单位
	@Column(name = "\"UNIT\"")
	private String unit;
	//下级单位
	@Column(name = "\"SUNITS\"")
	private String sunits;
	//维护班组
	@Column(name = "\"GROUPNAME\"")
	private String groupname;
	//设备工具类别
	@Column(name = "\"TOOLTYPE\"")
	private String tooltype;
	//设备工具名称
	@Column(name = "\"TOOLNAME\"")
	private String toolname;
	//检测周期
	@Column(name = "\"USELIFE\"")
	private String uselife;	
	//上一次检测时间
	@Column(name = "\"LASTCHECK\"")
	private String lastcheck;
	//最新检测时间
	@Column(name = "\"NEWCHECK\"")
	private String newcheck;
	//设备状态
	@Column(name = "\"DEVICESTATE\"")
	private String devicestate;
	public String getQrcode() {
		
		return qrcode;
	}
	public void setQrcode(String qrcode) {		
		this.qrcode = qrcode;
	}
	public String getUnit() {
		
		return unit;
	}
	public void setUnit(String unit) {
		
		this.unit = unit;
	}
	public String getSunits() {
		
		return sunits;
	}
	public void setSunits(String sunits) {
		
		this.sunits = sunits;
	}
	public String getGroupname() {
		
		return groupname;
	}
	public void setGroupname(String groupname) {
		
		this.groupname = groupname;
	}
	public String getTooltype() {
		
		return tooltype;
	}
	public void setTooltype(String tooltype) {
		
		this.tooltype = tooltype;
	}
	public String getToolname() {
		
		return toolname;
	}
	public void setToolname(String toolname) {
		
		this.toolname = toolname;
	}
	public String getUselife() {
		
		return uselife;
	}
	public void setUselife(String uselife) {
		
		this.uselife = uselife;
	}	
	public String getLastcheck() {
		
		return lastcheck;
	}
	public void setLastcheck(String lastcheck) {
		
		this.lastcheck = lastcheck;
	}
	public String getNewcheck() {
		
		return newcheck;
	}
	public void setNewcheck(String newcheck) {
		
		this.newcheck = newcheck;
	}
	public String getDevicestate() {
		
		return devicestate;
	}
	public void setDevicestate(String devicestate) {
		
		this.devicestate = devicestate;
	}
	
}

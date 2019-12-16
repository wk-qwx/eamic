package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qwx.util.Des3Util;

/**
 * 二维码记录表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"TL_QRCODE\"")
public class Qrcode2Entity extends BaseEntity{
	private static final long serialVersionUID = 1L;
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
	//数量
	@Column(name = "\"ITEM\"")
	private String item;
	//生成时间
	@Column(name = "\"CREATETIME\"")
	private String createtime;
	//生成批次号
	@Column(name = "\"BATCH\"")
	private String batch;
	//备注
	@Column(name = "\"REMARKS\"")
	private String remarks;
	
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
	public String getItem() {
		
		return item;
	}
	public void setItem(String item) {
		
		this.item = item;
	}
	public String getCreatetime() {
		
		return createtime;
	}
	public void setCreatetime(String createtime) {
		
		this.createtime = createtime;
	}
	public String getBatch() {
		
		return batch;
	}
	public void setBatch(String batch) {
		
		this.batch = batch;
	}
	public String getRemarks() {
		
		return remarks;
	}
	public void setRemarks(String remarks) {
		
		this.remarks = remarks;
	}
	
}

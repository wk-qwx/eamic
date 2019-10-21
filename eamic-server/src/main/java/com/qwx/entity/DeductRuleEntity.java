package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 扣分导则表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_DEDUCT_RULE\"")
public class DeductRuleEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//设备类型
	@Column(name = "\"DEVICETYPE\"")
	private String devicetype;
	//设备部件
	@Column(name = "\"UNIT\"")
	private String unit;
	//缺陷部位
	@Column(name = "\"DEFECTPLACE\"")
	private String defectplace;
	//缺陷类型
	@Column(name = "\"DEFECTTYPE\"")
	private String defecttype;
	//缺陷程度
	@Column(name = "\"DEFECTLEVEL\"")
	private String defectlevel;
	//缺陷级别
	@Column(name = "\"DEFECTSCALE\"")
	private String defectscale;
	//状态量扣分
	@Column(name = "\"DEDUCT\"")
	private String deduct;
	//设备状态
	@Column(name = "\"DEVICESTATUS\"")
	private String devicestatus;
	//检修原则
	@Column(name = "\"EXAMINERULE\"")
	private String examinerule;
	//检修内容
	@Column(name = "\"EXAMINECONTENT\"")
	private String examinecontent;
	
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDefectplace() {
		return defectplace;
	}
	public void setDefectplace(String defectplace) {
		this.defectplace = defectplace;
	}
	public String getDefecttype() {
		return defecttype;
	}
	public void setDefecttype(String defecttype) {
		this.defecttype = defecttype;
	}
	public String getDefectlevel() {
		return defectlevel;
	}
	public void setDefectlevel(String defectlevel) {
		this.defectlevel = defectlevel;
	}
	public String getDefectscale() {
		return defectscale;
	}
	public void setDefectscale(String defectscale) {
		this.defectscale = defectscale;
	}
	public String getDeduct() {
		return deduct;
	}
	public void setDeduct(String deduct) {
		this.deduct = deduct;
	}
	public String getDevicestatus() {
		return devicestatus;
	}
	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}
	public String getExaminerule() {
		return examinerule;
	}
	public void setExaminerule(String examinerule) {
		this.examinerule = examinerule;
	}
	public String getExaminecontent() {
		return examinecontent;
	}
	public void setExaminecontent(String examinecontent) {
		this.examinecontent = examinecontent;
	}
	
	
}

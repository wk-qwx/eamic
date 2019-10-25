package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 线路信息表
 * @author kal02
 *
 */
@Entity
public class RouteStateEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//缺陷部位
	@Column(name = "\"DEFECTPLACE\"")
	private String defectplace;
	//评分
	@Column(name = "\"SCORE\"")
	private String score;
	//设备状态
	@Column(name = "\"DEVICESTATUS\"")
	private String devicestatus;
	//线路名称
	@Column(name = "\"ROUTENAME\"")
	private String routename;
	//所属维护班组id
	@Column(name = "\"GROUPID\"")
	private String groupid;
	public String getDefectplace() {
		return defectplace;
	}
	public void setDefectplace(String defectplace) {
		this.defectplace = defectplace;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDevicestatus() {
		return devicestatus;
	}
	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	
		
}

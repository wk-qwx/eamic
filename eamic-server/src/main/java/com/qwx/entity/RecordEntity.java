package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 操作记录表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_RECORD\"")
public class RecordEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//关联缺陷信息
	@Column(name = "\"DEFECTID\"")
	private String defectid;
	//操作类型
	@Column(name = "\"OPERTYPE\"")
	private String opertype;
	//操作时间
	@Column(name = "\"OPERTIME\"")
	private String opertime;
	//操作用户id
	@Column(name = "\"USERID\"")
	private String userid;
	public String getDefectid() {
		return defectid;
	}
	public void setDefectid(String defectid) {
		this.defectid = defectid;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
		
}

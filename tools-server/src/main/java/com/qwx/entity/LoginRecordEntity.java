package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户登录日志表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"LOGIN_RECORD\"")
public class LoginRecordEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//用户id
	@Column(name = "\"USERID\"")
	private String userid;
	//登录方式
	@Column(name = "\"LOGINTYPE\"")
	private String logintype;
	//登录时的token
	@Column(name = "\"logintoken\"")
	private String logintoken;
	//登录时间
	@Column(name = "\"createtime\"")
	private String createtime;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public String getLogintoken() {
		return logintoken;
	}
	public void setLogintoken(String logintoken) {
		this.logintoken = logintoken;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}

package com.qwx.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户信息表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_USER\"")
public class UserEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	// 登录名
	@Column(name = "\"USERNAME\"")
	private String username;
	// 真实姓名
	@Column(name = "\"TRUENAME\"")
	private String truename;
	// 手机号码
	@Column(name = "\"PHONE\"")
	private String phone;
	// 密码
	@Column(name = "\"PASSWORD\"")
	private String password;
	// 编号
	@Column(name = "\"NO\"")
	private String no;	
	// 人员类别
	@Column(name = "\"GROUP\"")
	private String group;
	// 人员类别
	@Column(name = "\"GROUPID\"")
	private String groupid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}

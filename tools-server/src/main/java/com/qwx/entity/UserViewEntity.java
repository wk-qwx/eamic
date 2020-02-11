package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"USER_V\"")
public class UserViewEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//真实姓名
	@Column(name = "\"TRUENAME\"")
	private String truename;
	//性别
	@Column(name = "\"SEX\"")
	private String sex;
	//用户名
	@Column(name = "\"USERNAME\"")
	private String username;
	//密码
	@Column(name = "\"PWD\"")
	private String pwd;
	//手机号码
	@Column(name = "\"PHONE\"")
	private String phone;
	//所属班组
	@Column(name = "\"GROUPNAME\"")
	private String groupname;
	//所属三级单位
	@Column(name = "\"SUNITS\"")
	private String sunits;
	//创建时间
	@Column(name = "\"CREATETIME\"")
	private String createtime;
	//权限组
	@Column(name = "\"ACTIONS\"")
	private String actions;
	//权限名称
	@Column(name = "\"ACTIONNAME\"")
	private String actionname;
	//角色组
	@Column(name = "\"ROLES\"")
	private String roles;
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getSunits() {
		return sunits;
	}
	public void setSunits(String sunits) {
		this.sunits = sunits;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}

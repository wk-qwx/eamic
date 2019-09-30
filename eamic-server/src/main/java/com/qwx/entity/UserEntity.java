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
	@Column(name = "\"TYPE\"")
	private String type;
	// 创建日期
	@Column(name = "\"SYSCREATETIME\"")
	private Timestamp syscreatetime;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public Timestamp getSyscreatetime() {
		return syscreatetime;
	}

	public void setSyscreatetime(Timestamp syscreatetime) {
		this.syscreatetime = syscreatetime;
	}


}

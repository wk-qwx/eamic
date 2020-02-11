package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色用户映射表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"ROLE_USER\"")
public class RoleUserEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//角色id
	@Column(name = "\"ROLEID\"")
	private String roleid;
	//用户id
	@Column(name = "\"USERID\"")
	private String userid;
	//用户id
	@Column(name = "\"USERNAME\"")
	private String username;
	//创建者id
	@Column(name = "\"MASTERID\"")
	private String masterid;
	//创建者名称
	@Column(name = "\"MASTERNAME\"")
	private String mastername;
	//创建时间
	@Column(name = "\"CREATETIME\"")
	private String createtime;
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMasterid() {
		return masterid;
	}
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}

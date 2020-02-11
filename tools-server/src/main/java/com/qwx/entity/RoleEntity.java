package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"ROLE\"")
public class RoleEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//角色名称
	@Column(name = "\"ROLENAME\"")
	private String rolename;
	//角色信息
	@Column(name = "\"ROLEINFO\"")
	private String roleinfo;
	//创建者id
	@Column(name = "\"MASTERID\"")
	private String masterid;
	//创建者名称
	@Column(name = "\"MASTERNAME\"")
	private String mastername;
	//创建时间
	@Column(name = "\"CREATETIME\"")
	private String createtime;
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoleinfo() {
		return roleinfo;
	}
	public void setRoleinfo(String roleinfo) {
		this.roleinfo = roleinfo;
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

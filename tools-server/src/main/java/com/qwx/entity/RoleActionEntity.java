package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色权限映射表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"ROLE_ACTION\"")
public class RoleActionEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//角色id
	@Column(name = "\"ROLEID\"")
	private String roleid;
	//权限id
	@Column(name = "\"ACTIONID\"")
	private String actionid;
	//权限字符串
	@Column(name = "\"ACTION\"")
	private String action;
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
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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

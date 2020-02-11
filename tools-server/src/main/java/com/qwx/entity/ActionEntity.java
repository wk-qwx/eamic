package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限动作表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"ACTION\"")
public class ActionEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//动作权限名称
	@Column(name = "\"ACTIONNAME\"")
	private String actionname;
	//性别
	@Column(name = "\"ACTIONCOLUMNID\"")
	private String actioncolumnid;
	//动作权限字符串
	@Column(name = "\"ACTION\"")
	private String action;
	//是否可用
	@Column(name = "\"VIEWMODE\"")
	private String viewmode;
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getActioncolumnid() {
		return actioncolumnid;
	}
	public void setActioncolumnid(String actioncolumnid) {
		this.actioncolumnid = actioncolumnid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getViewmode() {
		return viewmode;
	}
	public void setViewmode(String viewmode) {
		this.viewmode = viewmode;
	}
	
}

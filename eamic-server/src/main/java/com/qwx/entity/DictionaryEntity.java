package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字典表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"DICTIONARY\"")
public class DictionaryEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//显示中文
	@Column(name = "\"DISPLAY\"")
	private String display;
	//中文代码
	@Column(name = "\"CODE\"")
	private String code;
	//字典名称
	@Column(name = "\"NAME\"")
	private String name;
	//级别
	@Column(name = "\"LEVEL\"")
	private String level;
	//排序
	@Column(name = "\"ORDER\"")
	private String order;
	//父id
	@Column(name = "\"PID\"")
	private String pid;
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}

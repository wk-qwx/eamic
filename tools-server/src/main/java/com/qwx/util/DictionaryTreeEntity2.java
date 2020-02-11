package com.qwx.util;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qwx.entity.BaseEntity;
import com.qwx.entity.Dictionary2Entity;

/**
 * 字典表
 * @author kal02
 *
 */
@Entity
public class DictionaryTreeEntity2 extends BaseEntity{
	//id
	@Column(name = "\"ID\"")
	private String id;
	//显示中文
	@Column(name = "\"DISPLAY\"")
	private String display;
	//显示中文
	@Column(name = "\"TITLE\"")
	private String title;
	//字典名
	@Column(name = "\"NAME\"")
	private String name;
	//中文代码
	@Column(name = "\"CODE\"")
	private String code;
	//级别
	@Column(name = "\"LEVEL\"")
	private String level;
	//父级
	@Column(name = "\"PID\"")
	private String pid;
	//子集
	@Column(name = "\"CHILDREN\"")
	private List<DictionaryTreeEntity2> children;
	
	public String getId() {
			
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplay() {
		
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getTitle() {
		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLevel() {
		
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<DictionaryTreeEntity2> getChildren() {
		
		return children;
	}
	public void setChildren(List<DictionaryTreeEntity2> children) {
		this.children = children;
	}
	
	
}

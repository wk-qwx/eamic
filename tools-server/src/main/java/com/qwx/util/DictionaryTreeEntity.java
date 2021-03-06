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
public class DictionaryTreeEntity extends BaseEntity{
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
	//中文代码
	@Column(name = "\"LEVEL\"")
	private String level;
	//子集
	@Column(name = "\"CHILDREN\"")
	private List<Dictionary2Entity> children;
	
	public String getId() {
			
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		
		return display;
	}
	public void setTitle(String display) {
		this.display = display;
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
	public List<Dictionary2Entity> getChildren() {
		
		return children;
	}
	public void setChildren(List<Dictionary2Entity> children) {
		this.children = children;
	}
	
	
}

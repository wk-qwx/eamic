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
	//显示中文
	@Column(name = "\"ID\"")
	private String id;
	//显示中文
	@Column(name = "\"DISPLAY\"")
	private String display;
	//中文代码
	@Column(name = "\"CODE\"")
	private String code;
	//子集
	@Column(name = "\"CHILDREN\"")
	private List<Dictionary2Entity> children;
	
	public String getId() {
			
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
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
	public List<Dictionary2Entity> getChildren() {
		
		return children;
	}
	public void setChildren(List<Dictionary2Entity> children) {
		this.children = children;
	}
	
	
}

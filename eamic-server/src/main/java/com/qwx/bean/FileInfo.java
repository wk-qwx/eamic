package com.qwx.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileInfo implements Serializable {
	static Map<String, List<String>> fileType = new HashMap<String, List<String>>();
	static {
		List<String> docType = new ArrayList<String>();
		docType.add(".doc");
		docType.add(".docx");
		docType.add(".ppt");
		docType.add(".pptx");
		docType.add(".xls");
		docType.add(".xlsx");
		docType.add(".pdf");
		docType.add(".txt");
		docType.add(".xml");
		fileType.put("文档", docType);
		List<String> imgType = new ArrayList<String>();
		imgType.add(".png");
		imgType.add(".jpg");
		imgType.add(".gif");
		docType.add(".bmp");
		docType.add(".svg");
		fileType.put("图片", imgType);
		List<String> audioType = new ArrayList<String>();
		audioType.add(".mp3");
		audioType.add(".wav");
		audioType.add(".ogg");
		fileType.put("音频", audioType);
		List<String> videoType = new ArrayList<String>();
		videoType.add(".mp4");
		videoType.add(".ogv");
		fileType.put("视频", videoType);
	}
	private static final long serialVersionUID = 1L;
	// 文件名称
	private String name;
	// 文件别名
	private String byname;
	// 文件扩展名
	private String extname;
	// 文件类型
	private String type = "未知";
	// 文件大小（KB）
	private Long size;
	// 文件路径
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setByname(String byname) {
		this.byname = byname;
	}

	public String getByname() {
		return byname;
	}

	public String getExtname() {
		return extname;
	}

	public void setExtname(String extname) {
		this.extname = extname;
		this.setType(extname);
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String extname) {
		String ext = extname.toLowerCase();
		if (fileType.get("文档").contains(ext))
			type = "文档";
		else if (fileType.get("图片").contains(ext))
			type = "图片";
		else if (fileType.get("音频").contains(ext))
			type = "音频";
		else if (fileType.get("视频").contains(ext))
			type = "视频";
	}
}

package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 参考资料
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_READMATERIAL\"")
public class ReadMaterialEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//文件名称
	@Column(name = "\"FILENAME\"")
	private String filename;
	//文件名称
	@Column(name = "\"FILETYPE\"")
	private String filetype;
	//上传时间
	@Column(name = "\"UPLOADDATE\"")
	private String uploaddate;
	//上传保存路径
	@Column(name = "\"UPLOADPATH\"")
	private String uploadpath;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	
}

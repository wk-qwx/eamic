package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qwx.util.Des3Util;

/**
 * 二维码记录表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"TL_QRCODE\"")
public class QrcodeEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//数量
	@Column(name = "\"ITEM\"")
	private String item;
	//生成时间
	@Column(name = "\"CREATETIME\"")
	private String createtime;
	//二维码文件保存路径
	@Column(name = "\"FILEPATH\"")
	private String filepath;
	//生成批次号
	@Column(name = "\"BATCH\"")
	private String batch;
	//备注
	@Column(name = "\"REMARKS\"")
	private String remarks;
	
	public String getItem() {
		try {
			item = Des3Util.encode(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	public void setItem(String item) {
		try {
			item = Des3Util.decode(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.item = item;
	}
	public String getCreatetime() {
		try {
			createtime = Des3Util.encode(createtime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createtime;
	}
	public void setCreatetime(String createtime) {
		try {
			createtime = Des3Util.decode(createtime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.createtime = createtime;
	}
	public String getFilepath() {
		try {
			filepath = Des3Util.encode(filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}
	public void setFilepath(String filepath) {
		try {
			filepath = Des3Util.decode(filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.filepath = filepath;
	}
	public String getBatch() {
		try {
			batch = Des3Util.encode(batch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return batch;
	}
	public void setBatch(String batch) {
		try {
			batch = Des3Util.decode(batch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.batch = batch;
	}
	public String getRemarks() {
		try {
			remarks = Des3Util.encode(remarks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remarks;
	}
	public void setRemarks(String remarks) {
		try {
			remarks = Des3Util.decode(remarks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.remarks = remarks;
	}
	
}

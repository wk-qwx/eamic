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
@Table(name = "\"QRCODE_V\"")
public class QrcodeViewEntity extends BaseEntity{
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
	
	//编码
	@Column(name = "\"QRCODE\"")
	private String qrcode;
	//单位
	@Column(name = "\"UNIT\"")
	private String unit;
	//下级单位
	@Column(name = "\"SUNITS\"")
	private String sunits;
	//维护班组
	@Column(name = "\"GROUPNAME\"")
	private String groupname;
	//设备工具类别
	@Column(name = "\"TOOLTYPE\"")
	private String tooltype;
	//设备工具名称
	@Column(name = "\"TOOLNAME\"")
	private String toolname;
	//检测周期
	@Column(name = "\"USELIFE\"")
	private String uselife;	
	//上一次检测时间
	@Column(name = "\"LASTCHECK\"")
	private String lastcheck;
	//最新检测时间
	@Column(name = "\"NEWCHECK\"")
	private String newcheck;
	//工器具状态
	@Column(name = "\"DEVICESTATE\"")
	private String devicestate;
	
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
	
	public String getQrcode() {
		try {
			qrcode = Des3Util.encode(qrcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		try {
			qrcode = Des3Util.decode(qrcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.qrcode = qrcode;
	}
	public String getUnit() {
		try {
			unit = Des3Util.encode(unit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unit;
	}
	public void setUnit(String unit) {
		try {
			unit = Des3Util.decode(unit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.unit = unit;
	}
	public String getSunits() {
		try {
			sunits = Des3Util.encode(sunits);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sunits;
	}
	public void setSunits(String sunits) {
		try {
			sunits = Des3Util.decode(sunits);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sunits = sunits;
	}
	public String getGroupname() {
		try {
			groupname = Des3Util.encode(groupname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupname;
	}
	public void setGroupname(String groupname) {
		try {
			groupname = Des3Util.decode(groupname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.groupname = groupname;
	}
	public String getTooltype() {
		try {
			tooltype = Des3Util.encode(tooltype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tooltype;
	}
	public void setTooltype(String tooltype) {
		try {
			tooltype = Des3Util.decode(tooltype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tooltype = tooltype;
	}
	public String getToolname() {
		try {
			toolname = Des3Util.encode(toolname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toolname;
	}
	public void setToolname(String toolname) {
		try {
			toolname = Des3Util.decode(toolname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.toolname = toolname;
	}
	public String getUselife() {
		try {
			uselife = Des3Util.encode(uselife);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uselife;
	}
	public void setUselife(String uselife) {
		try {
			uselife = Des3Util.decode(uselife);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.uselife = uselife;
	}
	
	public String getLastcheck() {
		try {
			lastcheck = Des3Util.encode(lastcheck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastcheck;
	}
	public void setLastcheck(String lastcheck) {
		try {
			lastcheck = Des3Util.decode(lastcheck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lastcheck = lastcheck;
	}
	public String getNewcheck() {
		try {
			newcheck = Des3Util.encode(newcheck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newcheck;
	}
	public void setNewcheck(String newcheck) {
		try {
			newcheck = Des3Util.decode(newcheck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.newcheck = newcheck;
	}
	public String getDevicestate() {
		try {
			devicestate = Des3Util.encode(devicestate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return devicestate;
	}
	public void setDevicestate(String devicestate) {
		try {
			devicestate = Des3Util.decode(devicestate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.devicestate = devicestate;
	}
	
}

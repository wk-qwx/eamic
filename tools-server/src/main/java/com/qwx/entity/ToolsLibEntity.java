package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qwx.util.Des3Util;

/**
 * 设备工具表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"TL_TOOLSLIB\"")
public class ToolsLibEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
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
	//设备状态
	@Column(name = "\"DEVICESTATE\"")
	private String devicestate;
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

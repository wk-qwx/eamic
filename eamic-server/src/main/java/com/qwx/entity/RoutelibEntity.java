package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 线路信息表
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_ROUTELIB\"")
public class RoutelibEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//电站类型
	@Column(name = "\"STATION\"")
	private String station;
	//电站类型id
	@Column(name = "\"STATIONID\"")
	private String stationid;
	//所属电站、电站名称
	@Column(name = "\"STATIONNAME\"")
	private String stationname;
	//线路名称
	@Column(name = "\"ROUTENAME\"")
	private String routename;
	//所属维护班组
	@Column(name = "\"GROUPNAME\"")
	private String groupname;
	//所属维护班组id
	@Column(name = "\"GROUPID\"")
	private String groupid;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
	public String getStationname() {
		return stationname;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
		
}

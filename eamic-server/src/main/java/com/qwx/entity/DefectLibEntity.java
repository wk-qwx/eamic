package com.qwx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 缺陷库视图
 * @author kal02
 *
 */
@Entity
@Table(name = "\"EA_DEFECTLIB_V\"")
public class DefectLibEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//电站类型
	@Column(name = "\"STATIONTYPE\"")
	private String stationtype;
	//电站名称
	@Column(name = "\"STATIONNAME\"")
	private String stationname;
	//线路
	@Column(name = "\"ROUTE\"")
	private String route;
	//设备类型
	@Column(name = "\"DEVICETYPE\"")
	private String devicetype;
	//设备名称
	@Column(name = "\"DEVICENAME\"")
	private String devicename;
	//部件
	@Column(name = "\"UNIT\"")
	private String unit;
	//等级
	@Column(name = "\"GRADE\"")
	private String grade;
	//投运时间
	@Column(name = "\"APPLYDATE\"")
	private String applydate;
	//发现日期
	@Column(name = "\"FINDDATE\"")
	private String finddate;
	//发现人员
	@Column(name = "\"FINDPEOPLE\"")
	private String findpeople;
	//所属班组名称
	@Column(name = "\"GROUPNAME\"")
	private String groupname;
	//所属班组id
	@Column(name = "\"GROUPID\"")
	private String groupid;
	//缺陷部位
	@Column(name = "\"DEFECTPLACE\"")
	private String defectplace;
	//缺陷类型
	@Column(name = "\"DEFECTTYPE\"")
	private String defecttype;
	//缺陷程度
	@Column(name = "\"DEFECTLEVEL\"")
	private String defectlevel;
	//缺陷等级
	@Column(name = "\"DEFECTSCALE\"")
	private String defectscale;
	//缺陷内容
	@Column(name = "\"DEFECTCONTENT\"")
	private String defectcontent;
	//设备评分
	@Column(name = "\"SCORE\"")
	private String score;
	//设备状态
	@Column(name = "\"DEVICESTATUS\"")
	private String devicestatus;
	//检修意见
	@Column(name = "\"OPINION\"")
	private String opinion;
	//缺陷部位局部照片
	@Column(name = "\"PHOTO1\"")
	private String photo1;
	//设备运行编码
	@Column(name = "\"PHOTO2\"")
	private String photo2;
	//整体照片
	@Column(name = "\"PHOTO3\"")
	private String photo3;
	//关联线路
	@Column(name = "\"ROUTEID\"")
	private String routeid;
	//关联评分导则
	@Column(name = "\"RULEID\"")
	private String ruleid;
	//关联登录用户
	@Column(name = "\"USERID\"")
	private String userid;
	//是否消缺
	@Column(name = "\"FLAG\"")
	private String flag;
	//最晚检修日期
	@Column(name = "\"EXAMINEDATE\"")
	private String examinedate;
	//检修原则
	@Column(name = "\"EXAMINERULE\"")
	private String examinerule;
	//检修内容
	@Column(name = "\"EXAMINECONTENT\"")
	private String examinecontent;
	//状态量扣分
	@Column(name = "\"DEDUCT\"")
	private String deduct;
	//消缺人员
	@Column(name = "\"SOLVEPEOPLE\"")
	private String solvepeople;
	//消缺日期
	@Column(name = "\"SOLVEDATE\"")
	private String solvedate;	
	//设备状态评价分析
	@Column(name = "\"ANALYSETXT\"")
	private String analysetxt;
	//检修类别
	@Column(name = "\"EXAMINETYPE\"")
	private String examinetype;
	//检修方式
	@Column(name = "\"EXAMINEMODE\"")
	private String examinemode;
	//是否是家族缺陷
	@Column(name = "\"FAMILYDEFECT\"")
	private String familydefect;
	//是否超期
	@Column(name = "\"ISTIME\"")
	private String istime;
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getStationname() {
		return stationname;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public String getFinddate() {
		return finddate;
	}
	public void setFinddate(String finddate) {
		this.finddate = finddate;
	}
	public String getFindpeople() {
		return findpeople;
	}
	public void setFindpeople(String findpeople) {
		this.findpeople = findpeople;
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
	public String getDefectplace() {
		return defectplace;
	}
	public void setDefectplace(String defectplace) {
		this.defectplace = defectplace;
	}
	public String getDefecttype() {
		return defecttype;
	}
	public void setDefecttype(String defecttype) {
		this.defecttype = defecttype;
	}
	public String getDefectlevel() {
		return defectlevel;
	}
	public void setDefectlevel(String defectlevel) {
		this.defectlevel = defectlevel;
	}
	public String getDefectscale() {
		return defectscale;
	}
	public void setDefectscale(String defectscale) {
		this.defectscale = defectscale;
	}
	public String getDefectcontent() {
		return defectcontent;
	}
	public void setDefectcontent(String defectcontent) {
		this.defectcontent = defectcontent;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDevicestatus() {
		return devicestatus;
	}
	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getRuleid() {
		return ruleid;
	}
	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getExaminedate() {
		return examinedate;
	}
	public void setExaminedate(String examinedate) {
		this.examinedate = examinedate;
	}
	public String getExaminerule() {
		return examinerule;
	}
	public void setExaminerule(String examinerule) {
		this.examinerule = examinerule;
	}
	public String getExaminecontent() {
		return examinecontent;
	}
	public void setExaminecontent(String examinecontent) {
		this.examinecontent = examinecontent;
	}
	public String getDeduct() {
		return deduct;
	}
	public void setDeduct(String deduct) {
		this.deduct = deduct;
	}
	public String getSolvepeople() {
		return solvepeople;
	}
	public void setSolvepeople(String solvepeople) {
		this.solvepeople = solvepeople;
	}
	public String getSolvedate() {
		return solvedate;
	}
	public void setSolvedate(String solvedate) {
		this.solvedate = solvedate;
	}
	public String getAnalysetxt() {
		return analysetxt;
	}
	public void setAnalysetxt(String analysetxt) {
		this.analysetxt = analysetxt;
	}
	public String getExaminetype() {
		return examinetype;
	}
	public void setExaminetype(String examinetype) {
		this.examinetype = examinetype;
	}
	public String getExaminemode() {
		return examinemode;
	}
	public void setExaminemode(String examinemode) {
		this.examinemode = examinemode;
	}
	public String getFamilydefect() {
		return familydefect;
	}
	public void setFamilydefect(String familydefect) {
		this.familydefect = familydefect;
	}
	public String getIstime() {
		return istime;
	}
	public void setIstime(String istime) {
		this.istime = istime;
	}
	
}

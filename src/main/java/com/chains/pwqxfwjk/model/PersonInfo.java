package com.chains.pwqxfwjk.model;

import java.util.Date;

/**
 * 类名称:PersonInfo<br>
 * 功能描述:  人员的基本信息和位置信息                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月26日 上午10:25:18<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月26日 上午10:25:18<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class PersonInfo {
	private String add;
	/**
	 * 当前位置
	 */
	private String currLocation;
	/**
	 * 企业id
	 */
	private String ecId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 传真
	 */
	private String faxnum;
	/**
	 * 成员编号
	 */
	private String memberCode;
	private String name;
	private String phone;
	/**
	 * 性别 1男 0女
	 */
	private String sex;
	/**
	 * 经度
	 */
	private String userLat;
	/**
	 * 纬度
	 */
	private String userLng;
	
	private Date createTime;
	
	private String memberId;
	
	private Integer status;
	
	private WqtMission wqtMission;
	
	private Serviceman serviceman;
	
	/**
	 * @since add
	 * @return String
	 */
	
	public String getAdd() {
		return add;
	}
	/**
	 * @since add
	 * @param add
	 * @return void
	 */
	
	public void setAdd(String add) {
		this.add = add;
	}
	/**
	 * @since 当前位置
	 * @return String
	 */
	
	public String getCurrLocation() {
		return currLocation;
	}
	/**
	 * @since 当前位置
	 * @param currLocation
	 * @return void
	 */
	
	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}
	/**
	 * @since 企业id
	 * @return String
	 */
	
	public String getEcId() {
		return ecId;
	}
	/**
	 * @since 企业id
	 * @param ecId
	 * @return void
	 */
	
	public void setEcId(String ecId) {
		this.ecId = ecId;
	}
	/**
	 * @since 邮箱
	 * @return String
	 */
	
	public String getEmail() {
		return email;
	}
	/**
	 * @since 邮箱
	 * @param email
	 * @return void
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @since 传真
	 * @return String
	 */
	
	public String getFaxnum() {
		return faxnum;
	}
	/**
	 * @since 传真
	 * @param faxnum
	 * @return void
	 */
	
	public void setFaxnum(String faxnum) {
		this.faxnum = faxnum;
	}
	/**
	 * @since 成员编号
	 * @return String
	 */
	
	public String getMemberCode() {
		return memberCode;
	}
	/**
	 * @since 成员编号
	 * @param memberCode
	 * @return void
	 */
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * @since name
	 * @return String
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @since name
	 * @param name
	 * @return void
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @since phone
	 * @return String
	 */
	
	public String getPhone() {
		return phone;
	}
	/**
	 * @since phone
	 * @param phone
	 * @return void
	 */
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @since 性别1男0女
	 * @return String
	 */
	
	public String getSex() {
		return sex;
	}
	/**
	 * @since 性别1男0女
	 * @param sex
	 * @return void
	 */
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @since 经度
	 * @return String
	 */
	/**
	 * @since 经度
	 * @return String
	 */
	
	public String getUserLat() {
		return userLat;
	}
	/**
	 * @since 经度
	 * @param userLat
	 * @return void
	 */
	
	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}
	/**
	 * @since 纬度
	 * @return String
	 */
	
	public String getUserLng() {
		return userLng;
	}
	/**
	 * @since 纬度
	 * @param userLng
	 * @return void
	 */
	
	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}
	/**
	 * @since createTime
	 * @return Date
	 */
	
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @since createTime
	 * @param createTime
	 * @return void
	 */
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @since memberId
	 * @return String
	 */
	
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @since memberId
	 * @param memberId
	 * @return void
	 */
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public WqtMission getWqtMission() {
		return wqtMission;
	}
	public void setWqtMission(WqtMission wqtMission) {
		this.wqtMission = wqtMission;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Serviceman getServiceman() {
		return serviceman;
	}
	public void setServiceman(Serviceman serviceman) {
		this.serviceman = serviceman;
	}
}

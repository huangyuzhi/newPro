package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SubstationInfo 
 *
 * @author 
 * @version	2015-11-5 13:58:52
 */
@Entity
@Table(name = "SUBSTATION_INFO")
public class SubstationInfo implements java.io.Serializable, GPSCoords {
	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String substationNumber;

	private String substationName;

	private String voltageLevel;

	private String powerBureauCode;

	private String powerBureauName;

	private String remarks;

	private String orderId;

	private String enabled;

	private String deleteStatus;

	private String createdBy;

	private String creationTime;

	private String creationDept;

	private String modifiedBy;

	private String modificationTime;

	private String modificationDept;

	private String deletedBy;

	private String deletedTime;

	private String deletedDept;
	
	/**
	 * 纬度.
	 */
	private String latitude;

	/**
	 * 经度.
	 */
	private String longitude;
	
	private Double bdCoorsX;
	
	private Double bdCoorsY; 
	
	public SubstationInfo() {
	}

	@Id
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SUBSTATION_NUMBER", length = 200)
	public String getSubstationNumber() {
		return this.substationNumber;
	}

	public void setSubstationNumber(String substationNumber) {
		this.substationNumber = substationNumber;
	}

	@Column(name = "SUBSTATION_NAME", length = 200)
	public String getSubstationName() {
		return this.substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	@Column(name = "VOLTAGE_LEVEL", length = 200)
	public String getVoltageLevel() {
		return this.voltageLevel;
	}

	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	@Column(name = "POWER_BUREAU_CODE", length = 200)
	public String getPowerBureauCode() {
		return this.powerBureauCode;
	}

	public void setPowerBureauCode(String powerBureauCode) {
		this.powerBureauCode = powerBureauCode;
	}

	@Column(name = "POWER_BUREAU_NAME", length = 200)
	public String getPowerBureauName() {
		return this.powerBureauName;
	}

	public void setPowerBureauName(String powerBureauName) {
		this.powerBureauName = powerBureauName;
	}

	@Column(name = "REMARKS", length = 1000)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "ORDER_ID", length = 100)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ENABLED", length = 10)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "DELETE_STATUS", length = 10)
	public String getDeleteStatus() {
		return this.deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Column(name = "CREATED_BY", length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATION_TIME", length = 100)
	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "CREATION_DEPT", length = 100)
	public String getCreationDept() {
		return this.creationDept;
	}

	public void setCreationDept(String creationDept) {
		this.creationDept = creationDept;
	}

	@Column(name = "MODIFIED_BY", length = 100)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFICATION_TIME", length = 100)
	public String getModificationTime() {
		return this.modificationTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Column(name = "MODIFICATION_DEPT", length = 100)
	public String getModificationDept() {
		return this.modificationDept;
	}

	public void setModificationDept(String modificationDept) {
		this.modificationDept = modificationDept;
	}

	@Column(name = "DELETED_BY", length = 100)
	public String getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	@Column(name = "DELETED_TIME", length = 100)
	public String getDeletedTime() {
		return this.deletedTime;
	}

	public void setDeletedTime(String deletedTime) {
		this.deletedTime = deletedTime;
	}

	@Column(name = "DELETED_DEPT", length = 100)
	public String getDeletedDept() {
		return this.deletedDept;
	}

	public void setDeletedDept(String deletedDept) {
		this.deletedDept = deletedDept;
	}

	/**
	 * @since 纬度.
	 * @return String
	 */
	@Column(name="LATITUDE", length=200)
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @since 纬度.
	 * @param latitude
	 * @return void
	 */
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @since 经度.
	 * @return String
	 */
	@Column(name="LONGITUDE", length=200)
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @since 经度.
	 * @param longitude
	 * @return void
	 */
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @since bdCoorsX
	 * @return Double
	 */
	@Column(name="BDCOORS_X")
	public Double getBdCoorsX() {
		return bdCoorsX;
	}

	/**
	 * @since bdCoorsX
	 * @param bdCoorsX
	 * @return void
	 */
	
	public void setBdCoorsX(Double bdCoorsX) {
		this.bdCoorsX = bdCoorsX;
	}

	/**
	 * @since bdCoorsY
	 * @return Double
	 */
	@Column(name="BDCOORS_Y")
	public Double getBdCoorsY() {
		return bdCoorsY;
	}

	/**
	 * @since bdCoorsY
	 * @param bdCoorsY
	 * @return void
	 */
	
	public void setBdCoorsY(Double bdCoorsY) {
		this.bdCoorsY = bdCoorsY;
	}
}

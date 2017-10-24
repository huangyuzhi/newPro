package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 35kVLineInfo
 * 
 * @author
 * @version 2015-11-5 13:58:52
 */
@Entity
@Table(name = "35kV_LINE_INFO")
public class LineInfoFor35kV implements java.io.Serializable, GPSCoords {

	/**
	 * 用一句话描述这个变量表示什么
	 */

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 所属变电站.
	 */
	private String belongSubstation;

	/**
	 * 所属线路.
	 */
	private String belongLine;

	/**
	 * 杆塔编号.
	 */
	private String towerNumber;

	/**
	 * 电压等级.
	 */
	private String voltageLevel;

	/**
	 * 杆塔类型.
	 */
	private String towerType;

	/**
	 * 纬度.
	 */
	private String latitude;

	/**
	 * 经度.
	 */
	private String longitude;

	/**
	 * 产权单位.
	 */
	private String propertyRightUnit;

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

	private Double bdCoorsX;
	
	private Double bdCoorsY;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public LineInfoFor35kV() {
		super();
	}

	public LineInfoFor35kV(Integer id) {
		super();
		this.id = id;
	}

	/**
	 * 所属变电站.
	 */
	@Column(name = "BELONG_SUBSTATION", length = 200)
	public String getBelongSubstation() {
		return this.belongSubstation;
	}

	/**
	 * 所属变电站.
	 */
	public void setBelongSubstation(String belongSubstation) {
		this.belongSubstation = belongSubstation;
	}

	/**
	 * 所属线路.
	 */

	@Column(name = "BELONG_LINE", length = 200)
	public String getBelongLine() {
		return this.belongLine;
	}

	/**
	 * 所属线路.
	 */
	public void setBelongLine(String belongLine) {
		this.belongLine = belongLine;
	}

	/**
	 * 杆塔编号.
	 */

	@Column(name = "TOWER_NUMBER", length = 200)
	public String getTowerNumber() {
		return this.towerNumber;
	}

	/**
	 * 杆塔编号.
	 */
	public void setTowerNumber(String towerNumber) {
		this.towerNumber = towerNumber;
	}

	/**
	 * 电压等级.
	 */

	@Column(name = "VOLTAGE_LEVEL", length = 200)
	public String getVoltageLevel() {
		return this.voltageLevel;
	}

	/**
	 * 电压等级.
	 */
	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	/**
	 * 杆塔类型.
	 */

	@Column(name = "TOWER_TYPE", length = 200)
	public String getTowerType() {
		return this.towerType;
	}

	/**
	 * 杆塔类型.
	 */
	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}

	/**
	 * 纬度.
	 */

	@Column(name = "LATITUDE", length = 200)
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * 纬度.
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 经度.
	 */

	@Column(name = "LONGITUDE", length = 200)
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * 经度.
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 产权单位.
	 */

	@Column(name = "PROPERTY_RIGHT_UNIT", length = 200)
	public String getPropertyRightUnit() {
		return this.propertyRightUnit;
	}

	/**
	 * 产权单位.
	 */
	public void setPropertyRightUnit(String propertyRightUnit) {
		this.propertyRightUnit = propertyRightUnit;
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
	 * @since bdCoorsX
	 * @return Double
	 */
	@Column(name = "BDCOORS_X")
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
	@Column(name = "BDCOORS_Y")
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

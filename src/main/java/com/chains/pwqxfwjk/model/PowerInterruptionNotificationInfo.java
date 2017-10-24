package com.chains.pwqxfwjk.model;

// 2015-12-23 11:07:59 3.2.2.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PowerInterruptionNotificationInfo 
 *
 * @author 
 * @version	2015-12-23 11:07:59
 */
@Entity
@Table(name = "POWER_INTERRUPTION_NOTIFICATION_INFO")
public class PowerInterruptionNotificationInfo implements java.io.Serializable {
	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;
	
	private static final String LINE_BREAK = "\n";
	
	private Integer id;

	/** 
	 * 计划停电：jhtd  
	临时停电：lstd  
	故障停电：gztd.
	 */
	private String powerInterruptionType;

	/** 
	 * 通知标题.
	 */
	private String notificationTitle;

	/** 
	 * 通知内容.
	 */
	private String notificationContent;

	/** 
	 * 影响区域.
	 */
	private String affectedArea;

	/** 
	 * 发布类型.
	 */
	private String releaseType;

	/** 
	 * 发布单位.
	 */
	private String releaseUnit;

	/** 
	 * 发布人
	.
	 */
	private String releasePerson;

	/** 
	 * 发布时间.
	 */
	private Date releaseTime;

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

	public PowerInterruptionNotificationInfo() {
	}

	public PowerInterruptionNotificationInfo(String powerInterruptionType) {
		this.powerInterruptionType = powerInterruptionType;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 
	 * 计划停电：jhtd  
	临时停电：lstd  
	故障停电：gztd.
	 */

	@Column(name = "POWER_INTERRUPTION_TYPE", nullable = false, length = 200)
	public String getPowerInterruptionType() {
		return this.powerInterruptionType;
	}

	/** 
	 * 计划停电：jhtd  
	临时停电：lstd  
	故障停电：gztd.
	 */
	public void setPowerInterruptionType(String powerInterruptionType) {
		this.powerInterruptionType = powerInterruptionType;
	}

	/** 
	 * 通知标题.
	 */

	@Column(name = "NOTIFICATION_TITLE", length = 200)
	public String getNotificationTitle() {
		return this.notificationTitle;
	}

	/** 
	 * 通知标题.
	 */
	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	/** 
	 * 通知内容.
	 */

	@Column(name = "NOTIFICATION_CONTENT", length = 4000)
	public String getNotificationContent() {
		return this.notificationContent;
	}

	/** 
	 * 通知内容.
	 */
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	/** 
	 * 影响区域.
	 */

	@Column(name = "AFFECTED_AREA", length = 200)
	public String getAffectedArea() {
		return this.affectedArea;
	}

	/** 
	 * 影响区域.
	 */
	public void setAffectedArea(String affectedArea) {
		this.affectedArea = affectedArea;
	}

	/** 
	 * 发布类型.
	 */

	@Column(name = "RELEASE_TYPE", length = 200)
	public String getReleaseType() {
		return this.releaseType;
	}

	/** 
	 * 发布类型.
	 */
	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}

	/** 
	 * 发布单位.
	 */

	@Column(name = "RELEASE_UNIT", length = 200)
	public String getReleaseUnit() {
		return this.releaseUnit;
	}

	/** 
	 * 发布单位.
	 */
	public void setReleaseUnit(String releaseUnit) {
		this.releaseUnit = releaseUnit;
	}

	/** 
	 * 发布人
	.
	 */

	@Column(name = "RELEASE_PERSON", length = 200)
	public String getReleasePerson() {
		return this.releasePerson;
	}

	/** 
	 * 发布人
	.
	 */
	public void setReleasePerson(String releasePerson) {
		this.releasePerson = releasePerson;
	}

	/** 
	 * 发布时间.
	 */

	@Column(name = "RELEASE_TIME")
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	/** 
	 * 发布时间.
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
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
	 * 方法名称:toString<br>
	 * 方法描述:重写方法                                                       <br>
	 * 特殊说明:                     <br>
	 * @return
	 * 返回类型:
	 * 
	 * @exception
	*/
	@Override
	public String toString() {
		String str = getNotificationTitle() != null ? getNotificationTitle() + LINE_BREAK : "";
		if(str.length() > 0) {	//通知有标题
			str += getNotificationContent() + LINE_BREAK;
		}
		str += getAffectedArea() != null ? "影响区域: " + getAffectedArea() + LINE_BREAK : "";
		str += getReleaseTime() != null ? "通知发布时间: " + new SimpleDateFormat("yyyy-MM-dd").format(getReleaseTime()) : "";
		return str;
	}
}

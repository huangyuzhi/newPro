package com.chains.pwqxfwjk.model;

// 2015-12-9 9:21:25 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PowerSituationInfo 
 *
 * @author 
 * @version	2015-12-9 9:21:25
 */
@Entity
@Table(name = "POWER_SITUATION_INFO")
public class PowerSituationInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String householderName;

	private String householderNumber;

	private String userPhoneNumber;

	private String queryMonth;

	private String queryTime;

	private String replyContent;

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

	public PowerSituationInfo() {
	}

	public PowerSituationInfo(String id) {
		this.id = id;
	}

	public PowerSituationInfo(String id, String householderName,
			String householderNumber, String userPhoneNumber,
			String queryMonth, String queryTime, String replyContent,
			String remarks, String orderId, String enabled,
			String deleteStatus, String createdBy, String creationTime,
			String creationDept, String modifiedBy, String modificationTime,
			String modificationDept, String deletedBy, String deletedTime,
			String deletedDept) {
		this.id = id;
		this.householderName = householderName;
		this.householderNumber = householderNumber;
		this.userPhoneNumber = userPhoneNumber;
		this.queryMonth = queryMonth;
		this.queryTime = queryTime;
		this.replyContent = replyContent;
		this.remarks = remarks;
		this.orderId = orderId;
		this.enabled = enabled;
		this.deleteStatus = deleteStatus;
		this.createdBy = createdBy;
		this.creationTime = creationTime;
		this.creationDept = creationDept;
		this.modifiedBy = modifiedBy;
		this.modificationTime = modificationTime;
		this.modificationDept = modificationDept;
		this.deletedBy = deletedBy;
		this.deletedTime = deletedTime;
		this.deletedDept = deletedDept;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "HOUSEHOLDER_NAME", length = 200)
	public String getHouseholderName() {
		return this.householderName;
	}

	public void setHouseholderName(String householderName) {
		this.householderName = householderName;
	}

	@Column(name = "HOUSEHOLDER_NUMBER", length = 200)
	public String getHouseholderNumber() {
		return this.householderNumber;
	}

	public void setHouseholderNumber(String householderNumber) {
		this.householderNumber = householderNumber;
	}

	@Column(name = "USER_PHONE_NUMBER", length = 200)
	public String getUserPhoneNumber() {
		return this.userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	@Column(name = "QUERY_MONTH", length = 200)
	public String getQueryMonth() {
		return this.queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	@Column(name = "QUERY_TIME", length = 200)
	public String getQueryTime() {
		return this.queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	@Column(name = "REPLY_CONTENT", length = 2000)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

}

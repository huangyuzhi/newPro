package com.chains.pwqxfwjk.model;

// 2015-12-9 9:21:25 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerComplianInfo 
 *
 * @author 
 * @version	2015-12-9 9:21:25
 */
@Entity
@Table(name = "CUSTOMER_COMPLIAN_INFO")
public class CustomerComplianInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String communicationType;

	private String complainUnit;

	private String complainContent;

	private String complainant;

	private String complainantPhone;

	private String complainTime;

	private String complainReply;

	private String disposalResult;

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

	public CustomerComplianInfo() {
	}

	public CustomerComplianInfo(Integer id) {
		this.id = id;
	}


	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "COMMUNICATION_TYPE", length = 200)
	public String getCommunicationType() {
		return this.communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	@Column(name = "COMPLAIN_UNIT", length = 200)
	public String getComplainUnit() {
		return this.complainUnit;
	}

	public void setComplainUnit(String complainUnit) {
		this.complainUnit = complainUnit;
	}

	@Column(name = "COMPLAIN_CONTENT", length = 200)
	public String getComplainContent() {
		return this.complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	@Column(name = "COMPLAINANT", length = 200)
	public String getComplainant() {
		return this.complainant;
	}

	public void setComplainant(String complainant) {
		this.complainant = complainant;
	}

	@Column(name = "COMPLAINANT_PHONE", length = 200)
	public String getComplainantPhone() {
		return this.complainantPhone;
	}

	public void setComplainantPhone(String complainantPhone) {
		this.complainantPhone = complainantPhone;
	}

	@Column(name = "COMPLAIN_TIME", length = 2000)
	public String getComplainTime() {
		return this.complainTime;
	}

	public void setComplainTime(String complainTime) {
		this.complainTime = complainTime;
	}

	@Column(name = "COMPLAIN_REPLY", length = 200)
	public String getComplainReply() {
		return this.complainReply;
	}

	public void setComplainReply(String complainReply) {
		this.complainReply = complainReply;
	}

	@Column(name = "DISPOSAL_RESULT", length = 200)
	public String getDisposalResult() {
		return this.disposalResult;
	}

	public void setDisposalResult(String disposalResult) {
		this.disposalResult = disposalResult;
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

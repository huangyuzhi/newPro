package com.chains.pwqxfwjk.model;

// 2015-12-9 9:21:25 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * PowerBusinessInfo 
 *
 * @author 
 * @version	2015-12-9 9:21:25
 */
/*@Entity
@Table(name = "POWER_BUSINESS_INFO")
@Inheritance(strategy=InheritanceType.JOINED)
@Polymorphism(type=PolymorphismType.EXPLICIT)*/
@MappedSuperclass
@DiscriminatorColumn(name="business_type")
public abstract class PowerBusinessInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	protected int id;

	protected String customerName;

	protected String electricityAddress;

	protected String identityCardNumber;

	protected String phoneNumber;

	/** 
	 * 业务类别有：
	           新装申请：xzsq
	           增容申请：zrsq
	           过户申请：ghsq
	           移表：ybsq
	           改类：glsq
	           暂拆：zcsq
	           销户：xhsq.
	 */
	protected String businessType;

	/** 
	 * 已提交申请：commit
	           正在处理：handling
	           处理完成：complate.
	 */
	protected String state;

	protected String remarks;

	protected String orderId;

	protected String enabled;

	protected String deleteStatus;

	protected String createdBy;

	protected String creationTime;

	protected String creationDept;

	protected String modifiedBy;

	protected String modificationTime;

	protected String modificationDept;

	protected String deletedBy;

	protected String deletedTime;

	protected String deletedDept;

	public PowerBusinessInfo() {
	}

	public PowerBusinessInfo(int id) {
		this.id = id;
	}


	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "customer_name", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "electricity_address", length = 100)
	public String getElectricityAddress() {
		return this.electricityAddress;
	}

	public void setElectricityAddress(String electricityAddress) {
		this.electricityAddress = electricityAddress;
	}

	@Column(name = "identity_card_number", length = 50)
	public String getIdentityCardNumber() {
		return this.identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	@Column(name = "phone_number", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** 
	 * 已提交申请：commit
	       正在处理：handling
	       处理完成：complate.
	 */

	@Column(name = "state", length = 50)
	public String getState() {
		return this.state;
	}

	/** 
	 * 已提交申请：commit
	       正在处理：handling
	       处理完成：complate.
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @since 业务类别有：新装申请：xzsq增容申请：zrsq过户申请：ghsq移表：ybsq改类：gbsq暂拆：zcsq销户：xhsq.
	 * @return String
	 */
	@Column(name="business_type")
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @since 业务类别有：新装申请：xzsq增容申请：zrsq过户申请：ghsq移表：ybsq改类：gbsq暂拆：zcsq销户：xhsq.
	 * @param businessType
	 * @return void
	 */
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}

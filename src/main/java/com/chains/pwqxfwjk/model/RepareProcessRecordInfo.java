package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RepareProcessRecordInfo 
 *
 * @author 
 * @version	2015-11-5 13:58:52
 */
@Entity
@Table(name = "REPARE_PROCESS_RECORD_INFO")
public class RepareProcessRecordInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String failLine;

	private String failArea;

	private String failType;

	private String dispatchedTime;

	private String repairPersonnel;

	private String repairVehicle;

	private String arrivalTime;

	private String completeRepairTime;

	private String powerResumptionTime;

	private String returnVisitSatisfaction;

	private String unsatisfiedReason;

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

	public RepareProcessRecordInfo() {
	}

	public RepareProcessRecordInfo(String id) {
		this.id = id;
	}

	public RepareProcessRecordInfo(String id, String failLine, String failArea,
			String failType, String dispatchedTime, String repairPersonnel,
			String repairVehicle, String arrivalTime,
			String completeRepairTime, String powerResumptionTime,
			String returnVisitSatisfaction, String unsatisfiedReason,
			String remarks, String orderId, String enabled,
			String deleteStatus, String createdBy, String creationTime,
			String creationDept, String modifiedBy, String modificationTime,
			String modificationDept, String deletedBy, String deletedTime,
			String deletedDept) {
		this.id = id;
		this.failLine = failLine;
		this.failArea = failArea;
		this.failType = failType;
		this.dispatchedTime = dispatchedTime;
		this.repairPersonnel = repairPersonnel;
		this.repairVehicle = repairVehicle;
		this.arrivalTime = arrivalTime;
		this.completeRepairTime = completeRepairTime;
		this.powerResumptionTime = powerResumptionTime;
		this.returnVisitSatisfaction = returnVisitSatisfaction;
		this.unsatisfiedReason = unsatisfiedReason;
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

	@Column(name = "FAIL_LINE", length = 200)
	public String getFailLine() {
		return this.failLine;
	}

	public void setFailLine(String failLine) {
		this.failLine = failLine;
	}

	@Column(name = "FAIL_AREA", length = 200)
	public String getFailArea() {
		return this.failArea;
	}

	public void setFailArea(String failArea) {
		this.failArea = failArea;
	}

	@Column(name = "FAIL_TYPE", length = 200)
	public String getFailType() {
		return this.failType;
	}

	public void setFailType(String failType) {
		this.failType = failType;
	}

	@Column(name = "DISPATCHED_TIME", length = 200)
	public String getDispatchedTime() {
		return this.dispatchedTime;
	}

	public void setDispatchedTime(String dispatchedTime) {
		this.dispatchedTime = dispatchedTime;
	}

	@Column(name = "REPAIR_PERSONNEL", length = 200)
	public String getRepairPersonnel() {
		return this.repairPersonnel;
	}

	public void setRepairPersonnel(String repairPersonnel) {
		this.repairPersonnel = repairPersonnel;
	}

	@Column(name = "REPAIR_VEHICLE", length = 200)
	public String getRepairVehicle() {
		return this.repairVehicle;
	}

	public void setRepairVehicle(String repairVehicle) {
		this.repairVehicle = repairVehicle;
	}

	@Column(name = "ARRIVAL_TIME", length = 200)
	public String getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Column(name = "COMPLETE_REPAIR_TIME", length = 200)
	public String getCompleteRepairTime() {
		return this.completeRepairTime;
	}

	public void setCompleteRepairTime(String completeRepairTime) {
		this.completeRepairTime = completeRepairTime;
	}

	@Column(name = "POWER_RESUMPTION_TIME", length = 200)
	public String getPowerResumptionTime() {
		return this.powerResumptionTime;
	}

	public void setPowerResumptionTime(String powerResumptionTime) {
		this.powerResumptionTime = powerResumptionTime;
	}

	@Column(name = "RETURN_VISIT_SATISFACTION", length = 200)
	public String getReturnVisitSatisfaction() {
		return this.returnVisitSatisfaction;
	}

	public void setReturnVisitSatisfaction(String returnVisitSatisfaction) {
		this.returnVisitSatisfaction = returnVisitSatisfaction;
	}

	@Column(name = "UNSATISFIED_REASON", length = 1000)
	public String getUnsatisfiedReason() {
		return this.unsatisfiedReason;
	}

	public void setUnsatisfiedReason(String unsatisfiedReason) {
		this.unsatisfiedReason = unsatisfiedReason;
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

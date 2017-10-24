package com.chains.pwqxfwjk.model;

// 2015-12-9 9:21:25 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PowerBusinessDetailInfo 
 *
 * @author 
 * @version	2015-12-9 9:21:25
 */
@Entity
@Table(name = "POWER_BUSINESS_DETAIL_INFO")
public class PowerBusinessDetailInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private int subid;

	private String charge;

	private String increaseCharge;

	private String transferReason;

	private String newCustomerName;

	private String newIdentityCardNumber;

	private String newPhone;

	private String accountNumber;

	private String originalElectricityType;

	private String newElectricityType;

	private String tableAddress;

	private String dismantleReason;

	public PowerBusinessDetailInfo() {
	}

	/**
	 * @since subid
	 * @return int
	 */
	@Id
	@Column(name = "subid", unique = true, nullable = false)
	public int getSubid() {
		return subid;
	}

	/**
	 * @since subid
	 * @param subid
	 * @return void
	 */
	public void setSubid(int subid) {
		this.subid = subid;
	}

	@Column(name = "charge", length = 50)
	public String getCharge() {
		return this.charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Column(name = "increase_charge", length = 50)
	public String getIncreaseCharge() {
		return this.increaseCharge;
	}

	public void setIncreaseCharge(String increaseCharge) {
		this.increaseCharge = increaseCharge;
	}

	@Column(name = "transfer_reason", length = 50)
	public String getTransferReason() {
		return this.transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	@Column(name = "new_customer_name", length = 50)
	public String getNewCustomerName() {
		return this.newCustomerName;
	}

	public void setNewCustomerName(String newCustomerName) {
		this.newCustomerName = newCustomerName;
	}

	@Column(name = "new_identity_card_number", length = 50)
	public String getNewIdentityCardNumber() {
		return this.newIdentityCardNumber;
	}

	public void setNewIdentityCardNumber(String newIdentityCardNumber) {
		this.newIdentityCardNumber = newIdentityCardNumber;
	}

	@Column(name = "new_phone", length = 50)
	public String getNewPhone() {
		return this.newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	@Column(name = "account_number", length = 50)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "original_electricity_type", length = 100)
	public String getOriginalElectricityType() {
		return this.originalElectricityType;
	}

	public void setOriginalElectricityType(String originalElectricityType) {
		this.originalElectricityType = originalElectricityType;
	}

	@Column(name = "new_electricity_type", length = 100)
	public String getNewElectricityType() {
		return this.newElectricityType;
	}

	public void setNewElectricityType(String newElectricityType) {
		this.newElectricityType = newElectricityType;
	}

	@Column(name = "table_address", length = 100)
	public String getTableAddress() {
		return this.tableAddress;
	}

	public void setTableAddress(String tableAddress) {
		this.tableAddress = tableAddress;
	}

	@Column(name = "dismantle_reason", length = 100)
	public String getDismantleReason() {
		return this.dismantleReason;
	}

	public void setDismantleReason(String dismantleReason) {
		this.dismantleReason = dismantleReason;
	}

}

package com.chains.pwqxfwjk.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * 类名称:Ghsq<br>
 * 功能描述:  过户申请                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月10日 上午8:40:20<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月10日 上午8:40:20<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */

@Entity
@Table(name = "POWER_BUSINESS_INFO")
@SecondaryTable(name="POWER_BUSINESS_DETAIL_INFO", pkJoinColumns={
		@PrimaryKeyJoinColumn(name="subid", referencedColumnName="id")
})
@DiscriminatorValue("ghsq")
public class Ghsq extends PowerBusinessInfo{

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 过户原因
	 */
	private String transferReason;
	
	/**
	 * 户号
	 */
	private String accountNumber;
	/**
	 * 新用户姓名
	 */
	private String newCustomerName;
	/**
	 * 新用户身份证号
	 */
	private String newIdentityCardNumber;
	/**
	 * 新用户电话号码
	 */
	private String newPhone;

	public Ghsq() {
		setBusinessType("ghsq");
	}
	
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "transfer_reason", length = 50)
	public String getTransferReason() {
		return this.transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}
	
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "new_customer_name", length = 50)
	public String getNewCustomerName() {
		return this.newCustomerName;
	}

	public void setNewCustomerName(String newCustomerName) {
		this.newCustomerName = newCustomerName;
	}
	
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "new_identity_card_number", length = 50)
	public String getNewIdentityCardNumber() {
		return this.newIdentityCardNumber;
	}

	public void setNewIdentityCardNumber(String newIdentityCardNumber) {
		this.newIdentityCardNumber = newIdentityCardNumber;
	}

	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "new_phone", length = 50)
	public String getNewPhone() {
		return this.newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "account_number", length = 50)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}

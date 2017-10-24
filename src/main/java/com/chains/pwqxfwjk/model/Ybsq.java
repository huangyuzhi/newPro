package com.chains.pwqxfwjk.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * 类名称:Ybsq<br>
 * 功能描述:    移表申请                  <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月10日 上午8:58:57<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月10日 上午8:58:57<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
@Entity
@Table(name = "POWER_BUSINESS_INFO")
@SecondaryTable(name="POWER_BUSINESS_DETAIL_INFO", pkJoinColumns={
		@PrimaryKeyJoinColumn(name="subid", referencedColumnName="id")
})
@DiscriminatorValue("ybsq")
public class Ybsq extends PowerBusinessInfo{
	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 户号
	 */
	private String accountNumber;
	/**
	 * 电表迁移位置
	 */
	private String tableAddress;
	
	public Ybsq() {
		setBusinessType("ybsq");
	}
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "account_number", length = 50)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "table_address", length = 100)
	public String getTableAddress() {
		return this.tableAddress;
	}

	public void setTableAddress(String tableAddress) {
		this.tableAddress = tableAddress;
	}
}

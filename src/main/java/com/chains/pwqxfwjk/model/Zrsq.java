package com.chains.pwqxfwjk.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * 类名称:Zrsq<br>
 * 功能描述:   增容申请                   <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月10日 上午8:59:34<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月10日 上午8:59:34<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
@Entity
@Table(name = "POWER_BUSINESS_INFO")
@SecondaryTable(name="POWER_BUSINESS_DETAIL_INFO", pkJoinColumns={
		@PrimaryKeyJoinColumn(name="subid", referencedColumnName="id")
})
@DiscriminatorValue("zrsq")
public class Zrsq extends PowerBusinessInfo {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前负荷
	 */
	private String charge;

	/**
	 * 新增负荷
	 */
	private String increaseCharge;
	
	public Zrsq() {
		setBusinessType("zrsq");
	}
	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "charge")
	public String getCharge() {
		return this.charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Column(table = "POWER_BUSINESS_DETAIL_INFO", name = "increase_charge")
	public String getIncreaseCharge() {
		return this.increaseCharge;
	}

	public void setIncreaseCharge(String increaseCharge) {
		this.increaseCharge = increaseCharge;
	}
}

package com.chains.pwqxfwjk.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * 类名称:Xzsq<br>
 * 功能描述: 供电业务申请——新装申请                     <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月9日 上午10:54:21<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月9日 上午10:54:21<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
@Entity
@Table(name = "POWER_BUSINESS_INFO")
@SecondaryTable(name="POWER_BUSINESS_DETAIL_INFO", pkJoinColumns={
		@PrimaryKeyJoinColumn(name="subid", referencedColumnName="id")
})
@DiscriminatorValue("xzsq")
public class Xzsq extends PowerBusinessInfo{
	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 1L;
	
	public Xzsq() {
		setBusinessType("xzsq");
	}
}

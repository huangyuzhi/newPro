package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerTransformerInfo 
 *
 * @author 
 * @version	2015-11-5 13:58:52
 */
@Entity
@Table(name = "CUSTOMER_TRANSFORMER_INFO")
public class CustomerTransformerInfo implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String lineName;

	private String transformerName;

	private String transformerNumber;

	private String customerNumber;

	private String customerName;

	private String matchKey;
	
	public CustomerTransformerInfo() {
	}
	
	public CustomerTransformerInfo(Integer id) {
		super();
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

	@Column(name = "LINE_NAME", length = 200)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "TRANSFORMER_NAME", length = 200)
	public String getTransformerName() {
		return this.transformerName;
	}

	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}

	@Column(name = "TRANSFORMER_NUMBER", length = 200)
	public String getTransformerNumber() {
		return this.transformerNumber;
	}

	public void setTransformerNumber(String transformerNumber) {
		this.transformerNumber = transformerNumber;
	}

	@Column(name = "CUSTOMER_NUMBER", length = 200)
	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Column(name = "CUSTOMER_NAME", length = 200)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "match_key", length = 30)
	public String getMatchKey() {
		return matchKey;
	}

	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}
}

package com.chains.pwqxfwjk.model;

import java.util.Date;

// 2015-12-25 15:29:08 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VCustomerInfo 
 *
 * @author 
 * @version	2015-12-25 15:29:08
 */
@Entity
@Table(name = "v_customer_info" )
public class VCustomerInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String customerName;

	private String customerNumber;

	private String electricType;

	private String electricTypeName;

	private String meteringMode;

	private String meteringModeName;

	private String voltageLevel;

	private String industryClassification;

	private String industryClassificationName;

	private String consumerCategory;

	private String consumerCategoryName;

	private String riskLevel;

	private String riskLevelName;

	private String urbanRuralCode;

	private String urbanRuralName;

	private String customerAddress;
	
	private String customerMobilePhone;
	
	private Date lastUpdateTime;
	
	private String updateField;
	@Id
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NAME", length = 200)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "CUSTOMER_NUMBER", length = 200)
	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Column(name = "ELECTRIC_TYPE", length = 200)
	public String getElectricType() {
		return this.electricType;
	}

	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}

	@Column(name = "ELECTRIC_TYPE_NAME", length = 100)
	public String getElectricTypeName() {
		return this.electricTypeName;
	}

	public void setElectricTypeName(String electricTypeName) {
		this.electricTypeName = electricTypeName;
	}

	@Column(name = "METERING_MODE", length = 200)
	public String getMeteringMode() {
		return this.meteringMode;
	}

	public void setMeteringMode(String meteringMode) {
		this.meteringMode = meteringMode;
	}

	@Column(name = "METERING_MODE_NAME", length = 100)
	public String getMeteringModeName() {
		return this.meteringModeName;
	}

	public void setMeteringModeName(String meteringModeName) {
		this.meteringModeName = meteringModeName;
	}

	@Column(name = "VOLTAGE_LEVEL", length = 200)
	public String getVoltageLevel() {
		return this.voltageLevel;
	}

	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	@Column(name = "INDUSTRY_CLASSIFICATION", length = 200)
	public String getIndustryClassification() {
		return this.industryClassification;
	}

	public void setIndustryClassification(String industryClassification) {
		this.industryClassification = industryClassification;
	}

	@Column(name = "INDUSTRY_CLASSIFICATION_NAME", length = 100)
	public String getIndustryClassificationName() {
		return this.industryClassificationName;
	}

	public void setIndustryClassificationName(String industryClassificationName) {
		this.industryClassificationName = industryClassificationName;
	}

	@Column(name = "CONSUMER_CATEGORY", length = 200)
	public String getConsumerCategory() {
		return this.consumerCategory;
	}

	public void setConsumerCategory(String consumerCategory) {
		this.consumerCategory = consumerCategory;
	}

	@Column(name = "CONSUMER_CATEGORY_NAME", length = 100)
	public String getConsumerCategoryName() {
		return this.consumerCategoryName;
	}

	public void setConsumerCategoryName(String consumerCategoryName) {
		this.consumerCategoryName = consumerCategoryName;
	}

	@Column(name = "RISK_LEVEL", length = 200)
	public String getRiskLevel() {
		return this.riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	@Column(name = "RISK_LEVEL_NAME", length = 100)
	public String getRiskLevelName() {
		return this.riskLevelName;
	}

	public void setRiskLevelName(String riskLevelName) {
		this.riskLevelName = riskLevelName;
	}

	@Column(name = "URBAN_RURAL_CODE", length = 200)
	public String getUrbanRuralCode() {
		return this.urbanRuralCode;
	}

	public void setUrbanRuralCode(String urbanRuralCode) {
		this.urbanRuralCode = urbanRuralCode;
	}

	@Column(name = "URBAN_RURAL_NAME", length = 100)
	public String getUrbanRuralName() {
		return this.urbanRuralName;
	}

	public void setUrbanRuralName(String urbanRuralName) {
		this.urbanRuralName = urbanRuralName;
	}

	@Column(name = "CUSTOMER_ADDRESS", length = 200)
	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @since customerMobilePhone
	 * @return String
	 */
	@Column(name = "CUSTOMER_MOBILE_PHONE", length = 200)
	public String getCustomerMobilePhone() {
		return customerMobilePhone;
	}

	/**
	 * @since customerMobilePhone
	 * @param customerMobilePhone
	 * @return void
	 */
	
	public void setCustomerMobilePhone(String customerMobilePhone) {
		this.customerMobilePhone = customerMobilePhone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_TIME")
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Column(name = "UPDATE_FIELD")
	public String getUpdateField() {
		return updateField;
	}

	public void setUpdateField(String updateField) {
		this.updateField = updateField;
	}
}

package com.chains.pwqxfwjk.model;
// Generated 2016-5-10 15:05:07 by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedNativeQuery;

@SqlResultSetMapping(name="VCustomerInfo",entities={
		@EntityResult(entityClass=VCustomerInfo.class)
})
	@NamedNativeQuery(
	    name = "findCustomerInfoV", 
		query = "SELECT ci.ID AS ID," +
				"ci.CUSTOMER_NAME AS CUSTOMER_NAME," +
				"ci.CUSTOMER_NUMBER AS CUSTOMER_NUMBER," +
				"ci.ELECTRIC_TYPE AS ELECTRIC_TYPE," +
				"cm.codeName AS ELECTRIC_TYPE_NAME," +
				"ci.METERING_MODE AS METERING_MODE," +
				"cm2.codeName AS METERING_MODE_NAME," +
				"ci.VOLTAGE_LEVEL AS VOLTAGE_LEVEL," +
				"ci.INDUSTRY_CLASSIFICATION AS INDUSTRY_CLASSIFICATION," +
				"cm3.codeName AS INDUSTRY_CLASSIFICATION_NAME," +
				"ci.CONSUMER_CATEGORY AS CONSUMER_CATEGORY," +
				"cm4.codeName AS CONSUMER_CATEGORY_NAME," +
				"ci.RISK_LEVEL AS RISK_LEVEL," +
				"cm5.codeName AS RISK_LEVEL_NAME," +
				"ci.URBAN_RURAL_CODE AS URBAN_RURAL_CODE," +
				"cm6.codeName AS URBAN_RURAL_NAME," +
				"ci.CUSTOMER_ADDRESS AS CUSTOMER_ADDRESS," +
				"ci.CUSTOMER_MOBILE_PHONE AS CUSTOMER_MOBILE_PHONE," + 
				"ci.LAST_UPDATE_TIME as LAST_UPDATE_TIME, " +
				"ci.UPDATE_FIELD as UPDATE_FIELD " +
				"FROM CUSTOMER_INFO ci " +
				"LEFT JOIN CODE_MAPPING cm " + 
				"ON ci.ELECTRIC_TYPE = cm.codeValue AND cm.codeType = 'ELECTRIC_TYPE' " +
				"LEFT JOIN CODE_MAPPING cm2 " + 
				"ON ci.METERING_MODE = cm2.codeValue AND cm2.codeType = 'METERING_MODE' " +
				"LEFT JOIN CODE_MAPPING cm3 " + 
				"ON ci.INDUSTRY_CLASSIFICATION = cm3.codeValue AND cm3.codeType = 'INDUSTRY_CLASSIFICATION' " +
				"LEFT JOIN CODE_MAPPING cm4 " +
				"ON ci.CONSUMER_CATEGORY = cm4.codeValue AND cm4.codeType = 'CONSUMER_CATEGORY' " +
				"LEFT JOIN CODE_MAPPING cm5 " + 
				"ON ci.RISK_LEVEL = cm5.codeValue AND cm5.codeType = 'RISK_LEVEL' " +
				"LEFT JOIN CODE_MAPPING cm6 " + 
				"ON ci.URBAN_RURAL_CODE = cm6.codeValue AND cm6.codeType = 'URBAN_RURAL_CODE'"
	, resultSetMapping="VCustomerInfo") 
@Entity
@Table(name = "CUSTOMER_INFO", uniqueConstraints = @UniqueConstraint(columnNames = "CUSTOMER_NUMBER"))
public class CustomerInfo implements java.io.Serializable {
	/**
	 * 不能添加非原子字段。
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerNumber;
	private String customerName;
	private String electricType;
	private String meteringMode;
	private String voltageLevel;
	private String industryClassification;
	private String consumerCategory;
	private String riskLevel;
	private String urbanRuralCode;
	private String customerAddress;
	private String customerFixedPhone;
	private String customerMobilePhone;
	private String relationPhones;
	private String belongTransformerNumber;
	private String powerUnit;
	private Date lastUpdateTime;
	private String updateField;
	
	public CustomerInfo() {
	}

	public CustomerInfo(Integer id, String customerNumber, String customerName, String electricType,
			String meteringMode, String voltageLevel, String industryClassification, String consumerCategory,
			String riskLevel, String urbanRuralCode, String customerAddress, String customerFixedPhone,
			String customerMobilePhone, String relationPhones, String belongTransformerNumber, String powerUnit,
			Date lastUpdateTime, String updateField) {
		super();
		this.id = id;
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.electricType = electricType;
		this.meteringMode = meteringMode;
		this.voltageLevel = voltageLevel;
		this.industryClassification = industryClassification;
		this.consumerCategory = consumerCategory;
		this.riskLevel = riskLevel;
		this.urbanRuralCode = urbanRuralCode;
		this.customerAddress = customerAddress;
		this.customerFixedPhone = customerFixedPhone;
		this.customerMobilePhone = customerMobilePhone;
		this.relationPhones = relationPhones;
		this.belongTransformerNumber = belongTransformerNumber;
		this.powerUnit = powerUnit;
		this.lastUpdateTime = lastUpdateTime;
		this.updateField = updateField;
	}



	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NUMBER", unique = true, length = 200)
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

	@Column(name = "ELECTRIC_TYPE", length = 200)
	public String getElectricType() {
		return this.electricType;
	}

	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}

	@Column(name = "METERING_MODE", length = 200)
	public String getMeteringMode() {
		return this.meteringMode;
	}

	public void setMeteringMode(String meteringMode) {
		this.meteringMode = meteringMode;
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

	@Column(name = "CONSUMER_CATEGORY", length = 200)
	public String getConsumerCategory() {
		return this.consumerCategory;
	}

	public void setConsumerCategory(String consumerCategory) {
		this.consumerCategory = consumerCategory;
	}

	@Column(name = "RISK_LEVEL", length = 200)
	public String getRiskLevel() {
		return this.riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	@Column(name = "URBAN_RURAL_CODE", length = 200)
	public String getUrbanRuralCode() {
		return this.urbanRuralCode;
	}

	public void setUrbanRuralCode(String urbanRuralCode) {
		this.urbanRuralCode = urbanRuralCode;
	}

	@Column(name = "CUSTOMER_ADDRESS", length = 200)
	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Column(name = "CUSTOMER_FIXED_PHONE", length = 200)
	public String getCustomerFixedPhone() {
		return this.customerFixedPhone;
	}

	public void setCustomerFixedPhone(String customerFixedPhone) {
		this.customerFixedPhone = customerFixedPhone;
	}

	@Column(name = "CUSTOMER_MOBILE_PHONE", length = 200)
	public String getCustomerMobilePhone() {
		return this.customerMobilePhone;
	}

	public void setCustomerMobilePhone(String customerMobilePhone) {
		this.customerMobilePhone = customerMobilePhone;
	}

	@Column(name = "RELATION_PHONES", length = 1000)
	public String getRelationPhones() {
		return this.relationPhones;
	}

	public void setRelationPhones(String relationPhones) {
		this.relationPhones = relationPhones;
	}

	@Column(name = "BELONG_TRANSFORMER_NUMBER", length = 200)
	public String getBelongTransformerNumber() {
		return this.belongTransformerNumber;
	}

	public void setBelongTransformerNumber(String belongTransformerNumber) {
		this.belongTransformerNumber = belongTransformerNumber;
	}

	@Column(name = "POWER_UNIT", length = 200)
	public String getPowerUnit() {
		return this.powerUnit;
	}

	public void setPowerUnit(String powerUnit) {
		this.powerUnit = powerUnit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_TIME", length = 19)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "UPDATE_FIELD", length = 200)
	public String getUpdateField() {
		return updateField;
	}

	public void setUpdateField(String updateField) {
		this.updateField = updateField;
	}

	
}

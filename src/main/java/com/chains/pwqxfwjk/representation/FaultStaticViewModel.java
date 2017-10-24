package com.chains.pwqxfwjk.representation;

import java.math.BigInteger;

public class FaultStaticViewModel {
	private String category;
	private String faultDevice;
	private String faultDeviceVoltageLevel;
	private String faultCategory;
	private BigInteger currentMonthAmount;
	private BigInteger totalAmount;
	private BigInteger lastMonthAmount;
	private BigInteger lastYearAmount;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigInteger getCurrentMonthAmount() {
		return currentMonthAmount;
	}
	public void setCurrentMonthAmount(BigInteger currentMonthAmount) {
		this.currentMonthAmount = currentMonthAmount;
	}
	public BigInteger getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigInteger totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigInteger getLastMonthAmount() {
		return lastMonthAmount;
	}
	public void setLastMonthAmount(BigInteger lastMonthAmount) {
		this.lastMonthAmount = lastMonthAmount;
	}
	public BigInteger getLastYearAmount() {
		return lastYearAmount;
	}
	public void setLastYearAmount(BigInteger lastYearAmount) {
		this.lastYearAmount = lastYearAmount;
	}
	public String getFaultDevice() {
		return faultDevice;
	}
	public void setFaultDevice(String faultDevice) {
		this.faultDevice = faultDevice;
	}
	public String getFaultDeviceVoltageLevel() {
		return faultDeviceVoltageLevel;
	}
	public void setFaultDeviceVoltageLevel(String faultDeviceVoltageLevel) {
		this.faultDeviceVoltageLevel = faultDeviceVoltageLevel;
	}
	public String getFaultCategory() {
		return faultCategory;
	}
	public void setFaultCategory(String faultCategory) {
		this.faultCategory = faultCategory;
	}
	
}

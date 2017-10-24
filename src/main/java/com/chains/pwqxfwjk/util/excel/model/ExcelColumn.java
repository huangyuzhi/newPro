package com.chains.pwqxfwjk.util.excel.model;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;

public abstract class ExcelColumn {
	private String titleName;
	private String property;
	
	public ExcelColumn(){}
	
	public ExcelColumn(String titleName, String properties) {
		this.titleName = titleName;
		this.property = properties;
	}
	
	public PropertyDescriptor getPropertyValue(Object dataObj) {
		PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(dataObj.getClass(), property);
		return descriptor;
	}
	
	public abstract String getFormatValue(int index, Object dataObj); 
	
	public String getTitle() {
		return titleName;
	}

	public String getProperty() {
		return property;
	}
}

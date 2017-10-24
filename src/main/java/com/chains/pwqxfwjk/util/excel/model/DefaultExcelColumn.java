package com.chains.pwqxfwjk.util.excel.model;

import com.chains.pwqxfwjk.util.excel.model.ExcelColumn;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultExcelColumn extends ExcelColumn {
	
	public DefaultExcelColumn() {
	}

	public DefaultExcelColumn(String titleName, String properties) {
		super(titleName, properties);
	}

	@Override
	public String getFormatValue(int index, Object dataObj) {
		PropertyDescriptor descriptor = getPropertyValue(dataObj);
		String result = "";
		try {
			Object obj = descriptor.getReadMethod().invoke(dataObj, (Object[])null);
			if(obj != null) {
				if(obj instanceof Date) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					result = sdf.format(obj);
				}else {
					result = obj.toString();
				}
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}

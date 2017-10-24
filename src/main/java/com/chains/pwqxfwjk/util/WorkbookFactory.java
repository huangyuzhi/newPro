package com.chains.pwqxfwjk.util;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookFactory {
	public static Workbook getWorkbook(String fileSuffix, InputStream in){
		Workbook instance = null;
		try {
			if(fileSuffix.equals(".xls")) {
				instance = new HSSFWorkbook(in);
			}else if(fileSuffix.equals(".xlsx")) {
				instance = new XSSFWorkbook(in);
			}else if(fileSuffix.equals(".et")) {
				instance = new HSSFWorkbook(in);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return instance;
	}
}

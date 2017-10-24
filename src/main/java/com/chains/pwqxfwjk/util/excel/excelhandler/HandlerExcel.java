package com.chains.pwqxfwjk.util.excel.excelhandler;

import java.io.File;
import java.io.FileInputStream;

import com.chains.pwqxfwjk.util.file.HandlerFile;
import com.chains.pwqxfwjk.util.WorkbookFactory;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * 类名称:HandlerExcel<br>
 * 功能描述:  处理excel的基类                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月13日 下午10:49:29<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月13日 下午10:49:29<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public abstract class HandlerExcel implements HandlerFile {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public final void handler(File file) {
		String fileName = file.getName();
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".")).trim();
		logger.info("正在处理文件：" + file.getPath());
		if(fileSuffix.equals(".xls") || fileSuffix.equals(".xlsx")) {
			//只对excel表格进行处理
			try {
				FileInputStream in = new FileInputStream(file);
				Workbook wb = WorkbookFactory.getWorkbook(fileSuffix, in);
				int sheetTotal = wb.getNumberOfSheets();
				for(int i = 0; i < sheetTotal; i++) {
					Sheet sheet = wb.getSheetAt(i);
					handlerSheet(sheet);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else {
			logger.info(file.getAbsolutePath() + " 文件已放弃处理");
		}
	}

	public boolean validateSheet(Sheet sheet) {
		Row row = sheet.getRow(1);
		boolean result = true;
		try {
			if(row != null) {
				if(row.getCell(0) == null || "".equals(ExcelUtil.getStringCellValue(row.getCell(0)).trim())) {
					result = false;
				}else if(!(ExcelUtil.getStringCellValue(row.getCell(0)).matches("\\d+\\.?\\d*"))) {
					result = false;
				}
			}else {
				result = false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public abstract void handlerSheet(Sheet sheet);
}

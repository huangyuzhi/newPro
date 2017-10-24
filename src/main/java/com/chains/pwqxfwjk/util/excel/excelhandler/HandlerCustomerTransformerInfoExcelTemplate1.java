package com.chains.pwqxfwjk.util.excel.excelhandler;

import com.chains.pwqxfwjk.model.CustomerTransformerInfo;
import com.chains.pwqxfwjk.service.CustomerTransformerInfoService;
import com.chains.pwqxfwjk.util.Exception.NoSuchExcelTypeException;
import com.chains.pwqxfwjk.util.file.HandlerFile;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新的户变关系excel表格的处理类
 * 处理变压器在表格内声明的excel
 * @author Administrator
 *
 */
@Component("handlerCustomerTransformerInfoExcelTemplate1")
public class HandlerCustomerTransformerInfoExcelTemplate1 implements HandlerFile {

	private final Logger logger = Logger.getLogger(this.getClass());
	private Integer titleRowIndex;
	private String transformerName;
	@Autowired
	private CustomerTransformerInfoService customerTransformerInfoService;

	@Override
	public void handler(File file) {
		logger.info("正在处理文件：" + file.getPath());
		try {
			Workbook wb = WorkbookFactory.create(file);
			transformerName = getTransformerNameByFileName(file);
			int sheetTotal = wb.getNumberOfSheets();
			for(int i = 0; i < sheetTotal; i++) {
				Sheet sheet = wb.getSheetAt(i);
				logger.info("handleing sheet : " + sheet.getSheetName());
				handlerSheet(sheet);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void handlerSheet(Sheet sheet) {
		if(!checkSheet(sheet)) {
//			throw new RuntimeException("不合格的sheet");
			return;
		}
		String transformerName = getTransformerNameBySheet(sheet);
		if(!checkTransformerName(transformerName)) {
			if(checkTransformerName(this.transformerName)) {
				transformerName = this.transformerName;
			}else {
				throw new RuntimeException("找不对有效的变压器名称");
			}
		}
		logger.info("变压器名称 : " + transformerName);
		if(transformerName.length() > 0) {
			//查找列
			Map<String, Integer> titleColumnNumPair = titleColumn(sheet);
			List<CustomerTransformerInfo> insertList = new ArrayList<>();
			for(int i = titleRowIndex + 1; !isEndRow(sheet.getRow(i)); i++) {
				Row row = sheet.getRow(i);
				CustomerTransformerInfo transformerInfo = new CustomerTransformerInfo();
				transformerInfo.setTransformerName(transformerName);
				transformerInfo.setCustomerNumber(ExcelUtil.getStringCellValue(row.getCell(titleColumnNumPair.get("客户编号"))));
				if(titleColumnNumPair.get("客户名") != null) {
					transformerInfo.setCustomerName(ExcelUtil.getStringCellValue(row.getCell(titleColumnNumPair.get("客户名"))));
				}
				insertList.add(transformerInfo);
			}
			logger.info("data rows size : " + insertList.size());
			customerTransformerInfoService.addList(insertList);
		}else {
			throw new RuntimeException("你逗我？");
		}
	}

	private Map<String, Integer> titleColumn(Sheet sheet) {
		Map<String, Integer> titleColumnNumPair = new HashMap<>();
		titleColumnNumPair.put("客户编号", null);
		titleColumnNumPair.put("客户名", null);
		for (int i = 0; i < 4; i++) {
			Row row = sheet.getRow(i);
			if(row == null) {
				continue;
			}
			for (Cell cell : row) {
				for (String titleName : titleColumnNumPair.keySet()) {
					if(ExcelUtil.getStringCellValue(cell).trim().equals(titleName)) {
						titleRowIndex = row.getRowNum();
						titleColumnNumPair.put(titleName, cell.getColumnIndex());
					}

					if(ExcelUtil.getStringCellValue(cell).trim().equals("用户编号") ) {
						titleColumnNumPair.put("客户编号", cell.getColumnIndex());
					}

					if(ExcelUtil.getStringCellValue(cell).trim().equals("用户名称")) {
						titleColumnNumPair.put("客户名", cell.getColumnIndex());
					}
				}
			}
		}
		return titleColumnNumPair;
	}
	
	private String getTransformerNameBySheet(Sheet sheet) {
		String transformerName = null;
		for (int i = 0; i < 4; i++) {
			if(sheet.getRow(i) != null) {
				String cellValue = ExcelUtil.getStringCellValue(sheet.getRow(i).getCell(0));
				if(cellValue != null && cellValue.contains("变压器名")) {
					String[] trans = cellValue.split(":");
					if(trans.length > 1) {
						transformerName = trans[1].trim();
					}
				}
			}
		}
		return transformerName;
	}
	
	private String getTransformerNameByFileName(File file) {
		String fileName = file.getName();
		String suffix = "";
		if(fileName.lastIndexOf(".") > -1) {
//			suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			fileName = fileName.substring(0,fileName.lastIndexOf("."));
		}
		return fileName;
	}
	
	private boolean checkTransformerName(String transformerName) {
		return transformerName != null && transformerName.trim().length() > 0;
	}

	//前三行的第一列都没有值则是空sheet
	private boolean checkSheet(Sheet sheet) {
		boolean result = false;
		for (int i = 0; i < 2; i++) {
			Row row = sheet.getRow(i);
			if(row != null && row.getCell(0) != null && row.getCell(0).getStringCellValue() != null && row.getCell(0).getStringCellValue().trim().length() > 0) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 判断是否是最后一行
	 */
	private boolean isEndRow(Row row) {
		boolean result = true;
		if(row != null && row.getCell(1) != null && ExcelUtil.getStringCellValue(row.getCell(1)).length() > 0) {
			result = false;
		}
		return result;
	}
}

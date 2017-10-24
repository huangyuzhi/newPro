package com.chains.pwqxfwjk.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.chains.pwqxfwjk.model.VCustomerInfo;

public class CustomerInfoExcel {
	private  Map<Integer, String> titleData;
	/**
	 * 主流程
	 * @throws Exception 
	 */
	public  byte[] majorFlow(List<VCustomerInfo> vCustomers) throws Exception {
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row titleRow = sheet.createRow(0);
		
		Font font = wb.createFont();
		font.setColor(Font.COLOR_RED);
		CellStyle updatedCellStyle = wb.createCellStyle();
		updatedCellStyle.setFont(font);
		
		createTitle(titleRow);
		for (int i = 0; i < vCustomers.size(); i++) {
			Row row = sheet.createRow(i+1);
			VCustomerInfo customer = vCustomers.get(i);
			List<String> updatedFields = new ArrayList<>();
			if(customer.getUpdateField() != null) {
				updatedFields = Arrays.asList(customer.getUpdateField().split(","));
			}
			
			Cell serialNumberCell = row.createCell(0);
			serialNumberCell.setCellValue(i+1);
			
			Cell customerNumCell = row.createCell(1);
			customerNumCell.setCellValue(customer.getCustomerNumber());
			if(updatedFields.contains("customerNumber")) {
				customerNumCell.setCellStyle(updatedCellStyle);
			}
			
			Cell customerNameCell = row.createCell(2);
			customerNameCell.setCellValue(customer.getCustomerName());
			if(updatedFields.contains("customerName")) {
				customerNameCell.setCellStyle(updatedCellStyle);
			}
			
			Cell electricTypeNameCell = row.createCell(3);
			electricTypeNameCell.setCellValue(customer.getElectricTypeName());
			if(updatedFields.contains("electricTypeName")) {
				electricTypeNameCell.setCellStyle(updatedCellStyle);
			}
			
			//计量方式
			Cell meteringModeNameCell = row.createCell(4);
			meteringModeNameCell.setCellValue(customer.getMeteringModeName());
			if(updatedFields.contains("meteringModeName")) {
				meteringModeNameCell.setCellStyle(updatedCellStyle);
			}
			
			//电压等级
			Cell voltageLevelCell = row.createCell(5);
			voltageLevelCell.setCellValue(customer.getVoltageLevel());
			if(updatedFields.contains("voltageLevel")) {
				voltageLevelCell.setCellStyle(updatedCellStyle);
			}
			
			//行业分类
			Cell industryClassificationNameCell = row.createCell(6);
			industryClassificationNameCell.setCellValue(customer.getIndustryClassificationName());
			if(updatedFields.contains("industryClassificationName")) {
				industryClassificationNameCell.setCellStyle(updatedCellStyle);
			}
			
			//用户类别
			Cell consumerCategoryNameCell = row.createCell(7);
			consumerCategoryNameCell.setCellValue(customer.getConsumerCategoryName());
			if(updatedFields.contains("consumerCategoryName")) {
				consumerCategoryNameCell.setCellStyle(updatedCellStyle);
			}
			
			//风险等级
			Cell riskLevelNameCell = row.createCell(8);
			riskLevelNameCell.setCellValue(customer.getRiskLevelName());
			if(updatedFields.contains("riskLevelName")) {
				riskLevelNameCell.setCellStyle(updatedCellStyle);
			}
			
			//城乡代码
			Cell urbanRuralNameCell = row.createCell(9);
			urbanRuralNameCell.setCellValue(customer.getUrbanRuralName());
			if(updatedFields.contains("urbanRuralName")) {
				urbanRuralNameCell.setCellStyle(updatedCellStyle);
			}
			
			//用电地址
			Cell customerAddressCell = row.createCell(10);
			customerAddressCell.setCellValue(customer.getCustomerAddress());
			if(updatedFields.contains("customerAddress")) {
				customerAddressCell.setCellStyle(updatedCellStyle);
			}
			
			//客户电话
			Cell customerPhoneCell = row.createCell(11);
			customerPhoneCell.setCellValue(customer.getCustomerMobilePhone());
			if(updatedFields.contains("customerMobilePhone")) {
				customerPhoneCell.setCellStyle(updatedCellStyle);
			}
		}
		
		ByteArrayOutputStream out =  new ByteArrayOutputStream();
		wb.write(out);
		return out.toByteArray(); 
	}

	
	private  void createTitle(Row titleRow) {
		titleData = new HashMap<>();
		titleData.put(0, "序号");
		titleData.put(1, "用户编号");
		titleData.put(2, "用户名称");
		titleData.put(3, "用电类别");
		titleData.put(4, "计量方式");
		titleData.put(5, "电压等级");
		titleData.put(6, "行业分类");
		titleData.put(7, "用户类别");
		titleData.put(8, "风险等级");
		titleData.put(9, "城乡代码");
		titleData.put(10, "用电地址");
		titleData.put(11, "客户电话");
		for (Integer columnNum : titleData.keySet()) {
			Cell cell = titleRow.createCell(columnNum);
			cell.setCellValue(titleData.get(columnNum));
		}
	}
}

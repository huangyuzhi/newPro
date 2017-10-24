package com.chains.pwqxfwjk.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chains.pwqxfwjk.util.excel.excelhandler.ExcelUtil;
import com.chains.pwqxfwjk.util.excel.model.IndexRange;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.chains.pwqxfwjk.representation.FaultStaticViewModel;
import com.chains.pwqxfwjk.util.excel.model.DefaultExcelColumn;
import com.chains.pwqxfwjk.util.excel.model.ExcelColumn;

@NotThreadSafe
public class FaultStaticExcel {
	private List<ExcelColumn> columns = new ArrayList<>();
	private Integer dataRowBeginNum;
	private List<String> groupFields = new ArrayList<>();
	
	public FaultStaticExcel(List<String> groupFields) {
		this.groupFields = groupFields;
		createColumnDefinition();
	}
	
	public byte[] majorFlow(List<FaultStaticViewModel> statics) throws Exception {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row titleRow = sheet.createRow(0);
		dataRowBeginNum = 1;
		createTitle(titleRow, wb);
		for (int i = 0; i < statics.size(); i++) {
			Row DataRow = sheet.createRow(i + dataRowBeginNum);
			FaultStaticViewModel staticViewModel = statics.get(i);
			
			for (int j = 0; j < columns.size(); j++) {
				Cell dataCell = DataRow.createCell(j);
				CellStyle cellStyle = wb.createCellStyle();
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);

				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				dataCell.setCellStyle(cellStyle);
				dataCell.setCellValue(columns.get(j).getFormatValue(i, staticViewModel));

				sheet.setColumnWidth(j, 4000);
			}
		}
		mergeSheet(sheet, statics.size(), groupFields);
		ByteArrayOutputStream out =  new ByteArrayOutputStream();
		wb.write(out);
		return out.toByteArray(); 
	}
	
	private void mergeSheet(Sheet sheet, int dataSize, List<String> groupFields) {
		mergeRow(dataRowBeginNum, dataRowBeginNum + dataSize - 1, sheet, groupFields, 0);
	}

	private void mergeRow(int beginIndex, int endIndex, Sheet sheet, List<String> groupFields, int groupFieldIndex) {
		int columnNum = getColumnNumBy(groupFields.get(groupFieldIndex));
		String previousFieldValue = ExcelUtil.getStringCellValue(sheet.getRow(beginIndex).getCell(columnNum));
		IndexRange indexRange = new IndexRange(beginIndex, beginIndex);
		for (int i = beginIndex + 1; i <= endIndex; i++) {
			String currentFieldValue = ExcelUtil.getStringCellValue(sheet.getRow(i).getCell(columnNum));
			if(currentFieldValue.equals(previousFieldValue)) {
				indexRange.increaseRange();
			}else {
				mergeing(sheet, groupFields, groupFieldIndex, columnNum, indexRange);
				previousFieldValue = currentFieldValue;
				indexRange = new IndexRange(i,i);
			}
		}
		mergeing(sheet, groupFields, groupFieldIndex, columnNum, indexRange);
	}

	private void mergeing(Sheet sheet, List<String> groupFields, int groupFieldIndex, int columnNum, IndexRange indexRange) {
		sheet.addMergedRegion(new CellRangeAddress(indexRange.getBeginIndex(),
				indexRange.getEndIndex(), columnNum, columnNum));
		if(groupFields.size() > groupFieldIndex + 1) {
			mergeRow(indexRange.getBeginIndex(), indexRange.getEndIndex(), sheet, groupFields, groupFieldIndex + 1);
		}
	}

	private void createTitle(Row titleRow, Workbook wb) {
		for (int i = 0; i < columns.size(); i++) {
			Cell titleCell = titleRow.createCell(i);
			titleCell.setCellValue(columns.get(i).getTitle());

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

			titleCell.setCellStyle(cellStyle);
		}
	}
	
	private void createColumnDefinition() {
		Map<String, String> zhMap = new HashMap<>();
		zhMap.put("category", "传入类别");
		zhMap.put("faultDevice", "故障设备");
		zhMap.put("faultDeviceVoltageLevel", "故障设备电压等级");
		zhMap.put("faultCategory", "故障类别");
		
		columns.add(new ExcelColumn("序列", "serialNumber") {
			@Override
			public String getFormatValue(int index, Object dataObj) {
				return index + 1 + "";
			}
		});
		for (String groupField : groupFields) {
			columns.add(new DefaultExcelColumn(zhMap.get(groupField), groupField));
		}
		columns.add(new DefaultExcelColumn("当月总计", "currentMonthAmount"));
		columns.add(new DefaultExcelColumn("累计", "totalAmount"));
		columns.add(new DefaultExcelColumn("上月总计", "lastMonthAmount"));
		columns.add(new DefaultExcelColumn("去年同月总计", "lastYearAmount"));
	}
	
	private Integer getColumnNumBy(String property) {
		for (int i = 0; i < columns.size(); i++) {
			if(columns.get(i).getProperty().equals(property)) {
				return i;
			}
		}
		return -1;
	}
}

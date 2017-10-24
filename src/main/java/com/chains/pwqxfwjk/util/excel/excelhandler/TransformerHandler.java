package com.chains.pwqxfwjk.util.excel.excelhandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.util.excel.ExcelMetadata;

public class TransformerHandler extends HandlerExcel{

	private TransformerInfoService transformerInfoService;
	

	public TransformerHandler(TransformerInfoService transformerInfoService) {
		this.transformerInfoService = transformerInfoService;
	}

	@Override
	public void handlerSheet(Sheet sheet) {
		ExcelMetadata metadata = new TransformerMetaData(sheet); 
		if(metadata.getBeginDataRow() != null) {
			int i = metadata.getBeginDataRow().getRowNum();
			List<TransformerInfo> list = new ArrayList<>();
			while(true) {
				Row row = sheet.getRow(i);
				if(metadata.isRowEnd(row)) {
					break;
				}
				list.add(fillTransformer(row));
				i++;
			}
			transformerInfoService.addList(list);
		}
	}

	private TransformerInfo fillTransformer(Row row) {
		TransformerInfo transformerInfo = new TransformerInfo();
		transformerInfo.setBelongSubstation(row.getCell(1).getStringCellValue());
		transformerInfo.setBelongLine(row.getCell(2).getStringCellValue());
		transformerInfo.setBelongBranch(row.getCell(3).getStringCellValue());
		transformerInfo.setTransformerName(row.getCell(4).getStringCellValue());
		transformerInfo.setTransformerType(row.getCell(5).getStringCellValue());
		transformerInfo.setTransformerNumber(ExcelUtil.getStringCellValue(row.getCell(8)));
		transformerInfo.setBelongLineNumber(ExcelUtil.getStringCellValue(row.getCell(9)));
		
		transformerInfo.setLatitude(row.getCell(11).getStringCellValue());
		transformerInfo.setLongitude(row.getCell(12).getStringCellValue());
		return transformerInfo;
	}

	private static class TransformerMetaData implements ExcelMetadata{
		private Sheet sheet;
		
		public TransformerMetaData(Sheet sheet) {
			this.sheet = sheet;
		}

		@Override
		public Row getTitleRow() {
			return sheet.getRow(0);
		}

		@Override
		public Row getBeginDataRow() {
			return sheet.getRow(2);
		}

		@Override
		public boolean isRowEnd(Row row) {
			if(row != null && row.getCell(4) != null && row.getCell(4).getStringCellValue() != null && ExcelUtil.getStringCellValue(row.getCell(4)).length() > 0) {
				return false;
			}
			return true;
		}
		
	}
}

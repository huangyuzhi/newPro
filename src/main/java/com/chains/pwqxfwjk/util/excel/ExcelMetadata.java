package com.chains.pwqxfwjk.util.excel;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelMetadata {
	Row getTitleRow();
	Row getBeginDataRow();
	boolean isRowEnd(Row row);
	
}

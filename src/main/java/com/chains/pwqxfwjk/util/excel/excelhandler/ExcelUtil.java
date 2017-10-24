package com.chains.pwqxfwjk.util.excel.excelhandler;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
    public static String getStringCellValue(Cell hssfCell) {
        String strCell;
        if (hssfCell == null) {
            return "";
        }
        switch (hssfCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = hssfCell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(hssfCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(hssfCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("")) {
            return "";
        }
        return strCell;
    }
}
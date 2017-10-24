package com.chains.pwqxfwjk.util.excel.excelhandler;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by Administrator on 2016/7/1.
 */
public interface SheetFilter {
    boolean accept(Workbook workbook, Sheet sheet);
}

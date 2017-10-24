package com.chains.pwqxfwjk.util.excel.excelhandler;

import com.chains.pwqxfwjk.model.CustomerTransformerInfo;
import com.chains.pwqxfwjk.service.CustomerTransformerInfoService;
import com.chains.pwqxfwjk.util.file.HandlerFile;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HandlerCustomerTransformerInfoExcelTemplate2 implements HandlerFile {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerTransformerInfoService customerTransformerInfoService;

    @Override
    public void handler(File file) {
        logger.info("正在处理文件：" + file.getPath());
        SheetFilter filter = new HandlerCustomerTransformerInfoExcelTemplate2.TransformerSheetFilter();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if(filter.accept(workbook, sheet)) {
                    logger.info("handleing sheet : " + sheet.getSheetName());
                    handlerSheet(sheet);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handlerSheet(Sheet sheet) {
        Map<String, Integer> titleMap = new HashMap<>();
        titleMap.put("客户编号", null);
        titleMap.put("客户名", null);
        titleMap.put("公变名称", null);

        Row titleRow = sheet.getRow(2);
        for (Cell cell :
                titleRow) {
            for (String key :
                    titleMap.keySet()) {
                if(ExcelUtil.getStringCellValue(cell).equals(key)) {
                    titleMap.put(key, cell.getColumnIndex());
                }
            }
        }

        List<CustomerTransformerInfo> list = new ArrayList<>();
        int beginDataRowIndex = 3;
        for (int i = beginDataRowIndex; !isRowEnd(sheet.getRow(i)) ; i++) {
            Row row = sheet.getRow(i);
            CustomerTransformerInfo customerTransformerInfo = new CustomerTransformerInfo();
            customerTransformerInfo.setTransformerName(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("公变名称"))));
            customerTransformerInfo.setCustomerNumber(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("客户编号"))));
            customerTransformerInfo.setCustomerName(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("客户名"))));

            list.add(customerTransformerInfo);
        }
        logger.info("数据行数：" + list.size());
        customerTransformerInfoService.addList(list);
    }

    private boolean isRowEnd(Row row) {
        return row == null || row.getCell(0) == null || ExcelUtil.getStringCellValue(row.getCell(0)).trim().length() == 0;
    }

    private static class TransformerSheetFilter implements SheetFilter {
        @Override
        public boolean accept(Workbook workbook, Sheet sheet) {
            return "Sheet1".equals(sheet.getSheetName());
        }
    }
}

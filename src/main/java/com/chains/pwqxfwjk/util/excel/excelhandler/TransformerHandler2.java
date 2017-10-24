package com.chains.pwqxfwjk.util.excel.excelhandler;

import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.service.TransformerInfoService;
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

@Component("transformerHandler2")
public class TransformerHandler2 implements HandlerFile {

    @Autowired
    private TransformerInfoService transformerInfoService;

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void handler(File file) {
        SheetFilter filter = new TransformerSheetFilter();
        try {
            logger.info(file.getAbsoluteFile());
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
        titleMap.put("站内变压器", null);
        titleMap.put("纬度", null);
        titleMap.put("经度", null);
        titleMap.put("ID", null);
        titleMap.put("变电站", null);
        titleMap.put("线路", null);
        titleMap.put("TRANSTYPE", null);

        Row titleRow = sheet.getRow(0);
        for (Cell cell :
                titleRow) {
            for (String key :
                    titleMap.keySet()) {
                if(ExcelUtil.getStringCellValue(cell).equals(key)) {
                    titleMap.put(key, cell.getColumnIndex());
                }
            }
        }
        List<TransformerInfo> list = new ArrayList<>();
        int beginDataRowIndex = 2;
        for (int i = beginDataRowIndex; !isRowEnd(sheet.getRow(i)) ; i++) {
            Row row = sheet.getRow(i);
            TransformerInfo transformerInfo = new TransformerInfo();
//            transformerInfo.setTransformerName(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("站内变压器"))));
//            transformerInfo.setBelongSubstation(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("变电站"))));
//            transformerInfo.setBelongLine(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("线路"))));
//            transformerInfo.setBelongBranch(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("所属分支*"))));
//            transformerInfo.setTransformerType(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("TRANSTYPE"))));
            transformerInfo.setTransformerNumber(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("ID"))));

            if(titleMap.get("纬度") != null) {
                transformerInfo.setLatitude(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("纬度"))));
                transformerInfo.setLongitude(ExcelUtil.getStringCellValue(row.getCell(titleMap.get("经度"))));
            }
            list.add(transformerInfo);
        }
        logger.info("数据行数：" + list.size());
        transformerInfoService.addList(list);
    }

    private boolean isRowEnd(Row row) {
        return row == null || row.getCell(0) == null || ExcelUtil.getStringCellValue(row.getCell(0)).trim().length() == 0;
    }

    private static class TransformerSheetFilter implements SheetFilter{

        @Override
        public boolean accept(Workbook workbook, Sheet sheet) {
            return  sheet != null && (sheet.getSheetName().contains("美式箱变（组合式）") || sheet.getSheetName().contains("欧式箱变（预装式）") );
//            return true;
//            return sheet.getSheetName().equals("配电变压器");
        }
    }
}

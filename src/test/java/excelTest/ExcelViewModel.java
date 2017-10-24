package excelTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.chains.pwqxfwjk.model.VCustomerInfo;

public class ExcelViewModel {
	private static Map<Integer, String> titleData;
	/**
	 * 主流程
	 * @throws Exception 
	 */
	public static <T>void majorFlow(List<VCustomerInfo> vCustomers) throws Exception {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row titleRow = sheet.createRow(0);
		createTitle(titleRow);
		for (int i = 0; i < vCustomers.size(); i++) {
			Row row = sheet.createRow(i+1);
			VCustomerInfo customer = vCustomers.get(i);
			Cell serialNumberCell = row.createCell(0);
			serialNumberCell.setCellValue(i+1);
			
			Cell customerNumCell = row.createCell(1);
			customerNumCell.setCellValue(customer.getCustomerNumber());
			
			Cell customerNameCell = row.createCell(2);
			customerNameCell.setCellValue(customer.getCustomerName());
			
			Cell electricTypeNameCell = row.createCell(3);
			electricTypeNameCell.setCellValue(customer.getElectricTypeName());
			
			//计量方式
			Cell meteringModeNameCell = row.createCell(4);
			meteringModeNameCell.setCellValue(customer.getMeteringModeName());
			
			//电压等级
			Cell voltageLevelCell = row.createCell(5);
			voltageLevelCell.setCellValue(customer.getVoltageLevel());
			
			//行业分类
			Cell industryClassificationNameCell = row.createCell(6);
			industryClassificationNameCell.setCellValue(customer.getIndustryClassificationName());
			
			//用户类别
			Cell consumerCategoryNameCell = row.createCell(7);
			consumerCategoryNameCell.setCellValue(customer.getConsumerCategoryName());
			
			//风险等级
			Cell riskLevelNameCell = row.createCell(8);
			riskLevelNameCell.setCellValue(customer.getRiskLevelName());
			
			//城乡代码
			Cell urbanRuralNameCell = row.createCell(9);
			urbanRuralNameCell.setCellValue(customer.getUrbanRuralName());
			
			//用电地址
			Cell customerAddressCell = row.createCell(10);
			customerAddressCell.setCellValue(customer.getCustomerAddress());
		}
		for (Integer columnNum : titleData.keySet()) {
			sheet.setColumnWidth(columnNum, 3000);
		}
		FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}


	private static void createTitle(Row titleRow) {
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
		for (Integer columnNum : titleData.keySet()) {
			Cell cell = titleRow.createCell(columnNum);
			cell.setCellValue(titleData.get(columnNum));
		}
	}

}

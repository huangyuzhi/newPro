package excelTest.poiusage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*createDateCell();*/
//		 fillAndColor();
//		 workingWithFont();
//		mergingCells();
		differentTypeCell();
	}

	private static void workingWithFont() throws FileNotFoundException, IOException {
		Workbook wb = new HSSFWorkbook();
		    Sheet sheet = wb.createSheet("new sheet");

		    // Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow(1);

		    // Create a new font and alter it.
		    Font font = wb.createFont();
		    font.setColor(Font.COLOR_RED);
		    font.setFontHeightInPoints((short)24);
		    font.setFontName("Courier New");
		    font.setItalic(true);
		    font.setStrikeout(true);

		    // Fonts are set into a style so create a new one to use.
		    CellStyle style = wb.createCellStyle();
		    style.setFont(font);

		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(1);
		    cell.setCellValue("This is a test of fonts");
		    cell.setCellStyle(style);

		    // Write the output to a file
		    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
	}

	private static void fillAndColor() throws FileNotFoundException, IOException {
		Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("new sheet");

		    // Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow((short) 1);

		    // Aqua background
		    CellStyle style = wb.createCellStyle();
		    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		    style.setFillPattern(CellStyle.BIG_SPOTS);
		    Cell cell = row.createCell((short) 1);
		    cell.setCellValue("X");
		    cell.setCellStyle(style);

		    // Orange "foreground", foreground being the fill foreground not the font color.
		    style = wb.createCellStyle();
		    style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    cell = row.createCell((short) 2);
		    cell.setCellValue("X");
		    cell.setCellStyle(style);

		    // Write the output to a file
		    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
	}

	private static void createDateCell() throws FileNotFoundException, IOException {
		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("new sheet");

		// Create a row and put some cells in it. Rows are 0 based.
		Row row = sheet.createRow(0);

		// Create a cell and put a date value in it. The first cell is not
		// styled
		// as a date.
		Cell cell = row.createCell(0);
		cell.setCellValue(new Date());

		// we style the second cell as a date (and time). It is important to
		// create a new cell style from the workbook otherwise you can end up
		// modifying the built in style and effecting not only this cell but
		// other cells.
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cell = row.createCell(1);
		cell.setCellValue(new Date());
		cell.setCellStyle(cellStyle);

		// you can also set date as java.util.Calendar
		cell = row.createCell(2);
		cell.setCellValue(Calendar.getInstance());
		cell.setCellStyle(cellStyle);

		//自动调整列宽
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		wb.write(fileOut);
		fileOut.close();
	}
	
	public static void differentTypeCell() {
		Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("new sheet");
	    Row row = sheet.createRow(0);
	    row.createCell(0).setCellValue(1.1);
	    row.createCell(1).setCellValue(new Date());
	    row.createCell(2).setCellValue(Calendar.getInstance());
	    row.createCell(3).setCellValue("a string");
	    row.createCell(4).setCellValue(true);
	    row.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);

	    // Write the output to a file
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("differentTypeCell.xlsx");
			wb.write(fileOut);
		    fileOut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	    
	}
	
	public static void mergingCells() {
		  Workbook wb = new HSSFWorkbook();
		    Sheet sheet = wb.createSheet("new sheet");

		    Row row = sheet.createRow(1);
		    Cell cell = row.createCell(1);
		    cell.setCellValue("This is a test of merging");
		    
		    sheet.addMergedRegion(new CellRangeAddress(
		            1, //first row (0-based)
		            2, //last row  (0-based)
		            1, //first column (0-based)
		            2  //last column  (0-based)
		    ));
		    // Write the output to a file
		    FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("mergingCells.xls");
				wb.write(fileOut);
			    fileOut.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		    
	}
}

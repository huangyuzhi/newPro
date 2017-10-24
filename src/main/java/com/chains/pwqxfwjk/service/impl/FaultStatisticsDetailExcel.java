package com.chains.pwqxfwjk.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;

public class FaultStatisticsDetailExcel {
	private  Map<Integer, String> titleData;
	/**
	 * 主流程
	 * @throws Exception 
	 */
	public byte[] majorFlow(List<FaultStatisticsDetail> list) throws Exception {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row titleRow = sheet.createRow(0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTitle(titleRow);
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i+1);
			FaultStatisticsDetail faultStatisticsDetail = list.get(i);
			//序号
			Cell serialNumberCell = row.createCell(0);
			serialNumberCell.setCellValue(i+1);
			
			//传入类别
			Cell categoryCell = row.createCell(1);
			categoryCell.setCellValue(faultStatisticsDetail.getCategory());
			
			//故障设备（线路、台区）
			Cell faultDeviceCell = row.createCell(2);
			faultDeviceCell.setCellValue(faultStatisticsDetail.getFaultDevice());
			
			//报修内容
			Cell repairContentCell = row.createCell(3);
			repairContentCell.setCellValue(faultStatisticsDetail.getRepairContent());
			
			//故障设备电压等级
			Cell faultDeviceVoltageLevelCell = row.createCell(4);
			faultDeviceVoltageLevelCell.setCellValue(faultStatisticsDetail.getFaultDeviceVoltageLevel());
			
			//故障类别
			Cell faultCategoryCell = row.createCell(5);
			faultCategoryCell.setCellValue(faultStatisticsDetail.getFaultCategory());
			
			//报修人（姓名）
			Cell reportCustomerNameCell = row.createCell(6);
			reportCustomerNameCell.setCellValue(faultStatisticsDetail.getReportCustomerName());
			
			//报修人（客户号）
			Cell reportCustomerNumberCell = row.createCell(7);
			reportCustomerNumberCell.setCellValue(faultStatisticsDetail.getReportCustomerNumber());
			
			//报修时间
			Cell reportTimeCell = row.createCell(8);
			reportTimeCell.setCellValue(faultStatisticsDetail.getReportTime() != null ? sdf.format(faultStatisticsDetail.getReportTime()) : null);
			
			//派工人
			Cell handlerUserCell = row.createCell(9);
			handlerUserCell.setCellValue(faultStatisticsDetail.getHandlerUser());
			
			//派工时间
			Cell sendMissionTimeCell = row.createCell(10);
			sendMissionTimeCell.setCellValue(faultStatisticsDetail.getSendMissionTime() != null ? sdf.format(faultStatisticsDetail.getSendMissionTime()) : null);
			
			//抢修人
			Cell performerCell = row.createCell(11);
			performerCell.setCellValue(faultStatisticsDetail.getPerformer());
			
			//抢修达到时间
			Cell performerArriveTimeCell = row.createCell(12);
			performerArriveTimeCell.setCellValue(faultStatisticsDetail.getPerformerArriveTime() != null ? sdf.format(faultStatisticsDetail.getPerformerArriveTime()) : null);
			
			//抢修结束时间
			Cell repairEndtimeCell = row.createCell(13);
			repairEndtimeCell.setCellValue(faultStatisticsDetail.getRepairEndtime() != null ? sdf.format(faultStatisticsDetail.getRepairEndtime()) : null);
			
			//到达时间超时
			Cell arriveTimeoutCell = row.createCell(14);
			arriveTimeoutCell.setCellValue(faultStatisticsDetail.getArriveTimeout() != null ?
					faultStatisticsDetail.getArriveTimeout() ? "是" : "否" : null);
			
			//抢修时间超时
			Cell repairTimeoutCell = row.createCell(15);
			repairTimeoutCell.setCellValue(faultStatisticsDetail.getRepairTimeout() != null ?
					faultStatisticsDetail.getRepairTimeout() ? "是" : "否" : null);
			
			//是否客户产权设备
			Cell customerDeviceCell = row.createCell(16);
			customerDeviceCell.setCellValue(faultStatisticsDetail.getCustomerDevice() != null ?
					faultStatisticsDetail.getCustomerDevice() ? "是" : "否" : null);
			
			//回访客户时间
			Cell reviewTimeCell = row.createCell(17); 
			reviewTimeCell.setCellValue(faultStatisticsDetail.getReviewTime() != null ? sdf.format(faultStatisticsDetail.getReviewTime()) : null);
			
			//回访人
			Cell reviewPersonCell = row.createCell(18); 
			reviewPersonCell.setCellValue(faultStatisticsDetail.getReviewPerson());
			
			//回访客户是否满意
			Cell satisfactionCell = row.createCell(19); 
			satisfactionCell.setCellValue(faultStatisticsDetail.getSatisfaction() != null ?
					faultStatisticsDetail.getSatisfaction() ? "是" : "否" : null);
			
			//备注 
			Cell descriptorCell = row.createCell(20);
			descriptorCell.setCellValue(faultStatisticsDetail.getDescriptor());
		}
		
		for(int i = 0; i < 21; i++) {
			sheet.setColumnWidth(i, 5000);
		}
		ByteArrayOutputStream out =  new ByteArrayOutputStream();
		wb.write(out);
		return out.toByteArray(); 
	}

	
	private  void createTitle(Row titleRow) {
		titleData = new HashMap<>();
		titleData.put(0, "序号");
		titleData.put(1, "传入类别");
		titleData.put(2, "故障设备（线路、台区）");
		titleData.put(3, "报修内容");
		titleData.put(4, "故障设备电压等级");
		titleData.put(5, "故障类别");
		titleData.put(6, "报修人（姓名）");
		titleData.put(7, "报修人（客户号）");
		titleData.put(8, "报修时间");
		titleData.put(9, "派工人");
		titleData.put(10, "派工时间");
		titleData.put(11, "抢修人");
		titleData.put(12, "抢修达到时间");
		titleData.put(13, "抢修结束时间");
		titleData.put(14, "到达时间超时");
		titleData.put(15, "抢修时间超时");
		titleData.put(16, "是否客户产权设备");
		titleData.put(17, "回访客户时间");
		titleData.put(18, "回访人");
		titleData.put(19, "回访客户是否满意");
		titleData.put(20, "备注");
		for (Integer columnNum : titleData.keySet()) {
			Cell cell = titleRow.createCell(columnNum);
			cell.setCellValue(titleData.get(columnNum));
		}
	}
}

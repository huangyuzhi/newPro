package com.chains.pwqxfwjk.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chains.core.util.ControllerSupport;
import com.chains.core.util.HandlerAction;
import com.chains.core.util.StringResult;
import com.chains.core.view.VPerson;
import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.FaultStaticViewModel;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.impl.FaultStatisticsDetailExcel;
import com.chains.pwqxfwjk.util.FaultStatisticsDetailQueryParam;
import com.chains.util.Json;
import com.chains.util.Page;
import com.chains.util.QcRowBounds;
import com.chains.util.StringUtil;

@Controller
@RequestMapping("/faultStatisticsDetail")
public class FaultStatisticsDetailController extends ControllerSupport{
	
	@Autowired
	private FaultStatisticsDetailService faultStatisticsDetailServiceImpl;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	dateFormat.setLenient(false);  
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
	
	@StringResult("权限不足")
	@RequestMapping("/maintainView")
	public String maintainView() {
		return "faultStatisticsDetail/maintainView";
	}
	
	@StringResult("权限不足")
	@RequestMapping("/statistics")
	public String statistics() {
		return "faultStatisticsDetail/statistics";
	}
	
	
	@StringResult("权限不足")
	@RequestMapping("/getData")
	@ResponseBody
	public Page<FaultStatisticsDetail> getData(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds, FaultStatisticsDetailQueryParam queryParam) {
		Page<FaultStatisticsDetail> page = new Page<>();
		page.setList(faultStatisticsDetailServiceImpl.find(faultStatisticsDetail, rowBounds, queryParam));
		page.setTotal(faultStatisticsDetailServiceImpl.count(faultStatisticsDetail, queryParam).intValue());
		return page;
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getViewModel")
	@ResponseBody
	public Page<FaultStaticViewModel> getViewModel(Integer year, Integer month, String groupField) {
		List<String> groupFields = StringUtil.getIdsToList(groupField);
		Page<FaultStaticViewModel> page = new Page<>();
		page.setList(faultStatisticsDetailServiceImpl.viewModel(year, month, groupFields));
		return page;
	}
	@RequestMapping("/del")
	@ResponseBody
	public Json del(final Integer id) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() {
				faultStatisticsDetailServiceImpl.del(id);
			}
		});
	}
	
	@RequestMapping("/assignMissionAndStatistics")
	@ResponseBody
	public Json assignMissionAndStatistics(final WqtMission wqtMission, final FaultStatisticsDetail faultStatisticsDetail, final TroubleLine troubleLine, HttpServletRequest request) {
		HttpSession session = request.getSession();
		VPerson person = (VPerson)session.getAttribute(session.getId() + "person");
		faultStatisticsDetail.setReportCustomerName(wqtMission.getConsumeName());
		faultStatisticsDetail.setReportCustomerNumber(wqtMission.getConsumePhone());
		faultStatisticsDetail.setHandlerUser(person.getPersonName());
		faultStatisticsDetail.setSendMissionTime(new Date());
		
		return handlerException(new HandlerAction() {
			@Override
			public void handler() throws Exception {
				faultStatisticsDetailServiceImpl.assignMissionAndStatistics(wqtMission, faultStatisticsDetail, troubleLine);
			}
		});
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds, FaultStatisticsDetailQueryParam queryParam) throws Exception {
		List<FaultStatisticsDetail> list = faultStatisticsDetailServiceImpl.find(faultStatisticsDetail, rowBounds,queryParam);
		byte[] bytes = new FaultStatisticsDetailExcel().majorFlow(list);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss");
		String fileName = "故障统计细节表";
		fileName += sdf.format(queryParam.getBeginDate()) + "-" + sdf.format(queryParam.getEndDate()) + ".xlsx";
	    HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment",  URLEncoder.encode(fileName, "UTF-8"));  
	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/staticExcel")
	public ResponseEntity<byte[]> staticExcel(Integer year, Integer month, String groupField) throws Exception {
		byte[] bytes = faultStatisticsDetailServiceImpl.staticExcel(year, month, StringUtil.getIdsToList(groupField));
		String fileName = "故障统计表";
		fileName += fileName + year + "年" + month + "月" + ".xlsx";
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment",  URLEncoder.encode(fileName, "UTF-8"));
	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/backfill")
	@ResponseBody
	public Json backfill(final FaultStatisticsDetail faultStatisticsDetail) {
		
		return handlerException(new HandlerAction() {
			@Override
			public void handler() throws Exception {
				faultStatisticsDetailServiceImpl.backfill(faultStatisticsDetail);
			}
		});
	}
	
}

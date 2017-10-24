package com.chains.pwqxfwjk.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.chains.pwqxfwjk.model.CustomerInfo;
import com.chains.pwqxfwjk.model.VCustomerInfo;
import com.chains.pwqxfwjk.service.CustomerInfoService;
import com.chains.pwqxfwjk.service.impl.CustomerInfoExcel;
import com.chains.pwqxfwjk.util.CustomerInfoQueryParam;
import com.chains.util.Json;
import com.chains.util.Page;
import com.chains.util.QcRowBounds;
import com.chains.util.StringUtil;

@Controller
@RequestMapping("/customer")
public class CustomerInfoController extends ControllerSupport {
	@Autowired
	private CustomerInfoService customerInfoServiceImpl;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	dateFormat.setLenient(false);  
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
	@StringResult("权限不足")
	@RequestMapping("/add")
	@ResponseBody
	public Json add(final CustomerInfo customerInfo) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() {
				customerInfoServiceImpl.add(customerInfo);
			}
		});
	}
	
	@StringResult("权限不足")
	@RequestMapping("/update")
	@ResponseBody
	public Json update(final CustomerInfo customerInfo) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() {
				customerInfoServiceImpl.update(customerInfo);
			}
		});
	}
	
	@RequestMapping("/downloadExcel")  
	public ResponseEntity<byte[]> download(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds) throws Exception {
		List<VCustomerInfo> customers = customerInfoServiceImpl.getCustomerVList(customerInfoQueryParam, rowBounds);
		byte[] bytes = new CustomerInfoExcel() .majorFlow(customers);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss");
		String fileName = "客户变更记录";
		fileName += sdf.format(customerInfoQueryParam.getBeginDate()) + "-" + sdf.format(customerInfoQueryParam.getEndDate()) + ".xlsx";
	    HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment",  URLEncoder.encode(fileName, "UTF-8"));  
	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);  
	}  
	@StringResult("权限不足")
	@RequestMapping("/del")
	@ResponseBody
	public Json del(final String ids) {
		
		return handlerException(new HandlerAction() {
			@Override
			public void handler() {
				List<String> idList = StringUtil.getIdsToList(ids);
				List<Integer> IntgerIds = new ArrayList<Integer>();
				for(String id : idList) {
					IntgerIds.add(Integer.parseInt(id));
				}
				customerInfoServiceImpl.del(IntgerIds);
			}
		});
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getData")
	@ResponseBody
	public Page<VCustomerInfo> getData(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds, HttpServletRequest request) throws InterruptedException {
		Page<VCustomerInfo> page = customerInfoServiceImpl.getCustomerV(customerInfoQueryParam, rowBounds);
		return page;
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getCodeMapping")
	@ResponseBody
	public List<Map<String, Object>> getCodeMapping(String codeType) {
		 List<Map<String, Object>> list = customerInfoServiceImpl.getCodeMapping(codeType);
		 return list;
	}

	@StringResult("权限不足")
	@RequestMapping("/findByCustomerNumber")
	@ResponseBody
	public List<CustomerInfo> findByCustomerNumber(String customerNumber) {
		return customerInfoServiceImpl.findByCustomerNumber(customerNumber);
	}

	
	@StringResult("权限不足")
	@RequestMapping("/findByExample")
	@ResponseBody
	public List<CustomerInfo> findByExample(CustomerInfo customerInfo) {
		return customerInfoServiceImpl.findByExample(customerInfo);
	}
	@StringResult("权限不足")
	@RequestMapping("/maintainView")
	public String maintainView(){
		return "tableindex";
	}
	
	@StringResult("权限不足")
	@RequestMapping("/customerChangeInfo")
	public String customerChangeInfo(){
		return "customerChangeInfo";
	}
	
}

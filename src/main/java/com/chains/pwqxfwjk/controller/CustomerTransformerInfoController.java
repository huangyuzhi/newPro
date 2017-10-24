package com.chains.pwqxfwjk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.CustomerTransformerInfo;
import com.chains.pwqxfwjk.service.CustomerTransformerInfoService;

@Controller
@RequestMapping("/customerTransformerInfo")
public class CustomerTransformerInfoController {
	@Autowired
	private CustomerTransformerInfoService customerTransformerInfoServiceImpl;
	
	@StringResult("权限不足")
	@RequestMapping("/getData")
	public String getData(HttpServletRequest request, String customerNumber) {
		 List<CustomerTransformerInfo> list = customerTransformerInfoServiceImpl.getData(customerNumber);
		request.setAttribute("data", JSON.toJSONString(list));
		return "jsonp";
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getDataWithJson")
	public List<CustomerTransformerInfo> getDataWithJson(String searchKey) {
		List<CustomerTransformerInfo> list = new ArrayList<CustomerTransformerInfo>();
		if(searchKey != null && searchKey.matches("\\d+")) {
			list = customerTransformerInfoServiceImpl.getData(searchKey);
		}else {
			list = customerTransformerInfoServiceImpl.getByCostomerName(searchKey);
		}
		return list;
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getTransByCustomerNumber")
	public List<CustomerTransformerInfo> getTransByCustomerNumber(String customerNumber) {
		return customerTransformerInfoServiceImpl.getData(customerNumber);
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getByCostomerNumber")
	public String getByCostomerNumber(HttpServletRequest request, String costomerNumber) {
		CustomerTransformerInfo data = customerTransformerInfoServiceImpl.getByCostomerNumber(costomerNumber);
		request.setAttribute("data", JSON.toJSONString(data));
		return "jsonp";
	}
}

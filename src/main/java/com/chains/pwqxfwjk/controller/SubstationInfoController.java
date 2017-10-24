package com.chains.pwqxfwjk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.SubstationInfo;
import com.chains.pwqxfwjk.service.SubstationInfoService;
import com.chains.pwqxfwjk.util.SubstationInfoQueryParam;
import com.chains.util.QcRowBounds;

@Controller
@RequestMapping("/substation")
public class SubstationInfoController {
	@Autowired
	private SubstationInfoService SubstationInfoServiceImpl;
	
	@StringResult("权限不足")
	@RequestMapping("/getData")
	public String getData(HttpServletRequest request, SubstationInfoQueryParam queryParam, QcRowBounds rowBounds) {
		List<SubstationInfo> list = SubstationInfoServiceImpl.getData(queryParam, rowBounds);
		request.setAttribute("data", JSON.toJSONString(list));
		return "jsonp";
	}

}

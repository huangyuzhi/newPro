package com.chains.pwqxfwjk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.chains.core.util.ControllerSupport;
import com.chains.core.util.HandlerAction;
import com.chains.util.Json;
import com.chains.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.pwqxfwjk.util.WebUtil;
import com.chains.util.QcRowBounds;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/transformerInfo")
public class TransformerInfoController extends ControllerSupport {
	@Autowired
	private TransformerInfoService transformerInfoServiceImpl;
	
	@SuppressWarnings("SpringMVCViewInspection")
	@StringResult("权限不足")
	@RequestMapping("/getData")
	public String getData(HttpServletRequest request, TransformerInfoQueryParam queryParam, QcRowBounds rowBounds) {
		List<TransformerInfo> list = transformerInfoServiceImpl.getData(queryParam, rowBounds);
		request.setAttribute("data", JSON.toJSONString(list));
		return "jsonp";
	}

	@StringResult("权限不足")
	@RequestMapping("/pageData")
	@ResponseBody
	public Page<TransformerInfo> pageData(TransformerInfoQueryParam queryParam, QcRowBounds rowBounds) {
		Page<TransformerInfo> page = new Page<>();
		page.setList(transformerInfoServiceImpl.getData(queryParam, rowBounds));
		page.setTotal(transformerInfoServiceImpl.count(queryParam).intValue());
		return page;
	}

	@StringResult("权限不足")
	@RequestMapping("/getDataWithJson")
	@ResponseBody
	public List<TransformerInfo> getData(TransformerInfoQueryParam queryParam) {
		return transformerInfoServiceImpl.getData(queryParam, new QcRowBounds());
	}
	
	@StringResult("权限不足")
	@RequestMapping("/findFaultTransformer")
	@ResponseBody
	public List<TransformerInfo> findFaultTransformer() {
		TransformerInfo transformerInfo = new TransformerInfo();
		transformerInfo.setIsFault(true);
		return transformerInfoServiceImpl.findByExample(transformerInfo);
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getTransformer")
	@ResponseBody
	public List<TransformerInfo> getTransformer( String searchKey) {
		List<TransformerInfo> transformers = new ArrayList<>();
		try {
			if(searchKey != null && searchKey.matches("\\d+")) {
				TransformerInfo transformerInfo = transformerInfoServiceImpl.getTransformerByUser(searchKey);
				if(transformerInfo != null) {
					transformers.add(transformerInfo);
				}
			}else {
				transformers = transformerInfoServiceImpl.getTransformerByCustomerName(searchKey);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return transformers;
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getTransformerByLine")
	public String getTransformerByLine(HttpServletRequest request, String lineName) {
		if("GET".equals(request.getMethod())) {
			lineName = WebUtil.transToUTF(lineName, "ISO-8859-1", "UTF-8");
		}
		TransformerInfoQueryParam queryParam = new TransformerInfoQueryParam();
		queryParam.setLineName(lineName);
		List<TransformerInfo> list = transformerInfoServiceImpl.getData(queryParam, new QcRowBounds());
		request.setAttribute("data", JSON.toJSONString(list));
		return "jsonp";
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getTransformerByLineWithJson")
	public List<TransformerInfo> getTransformerByLineWithJson(String lineName) {
		TransformerInfoQueryParam queryParam = new TransformerInfoQueryParam();
		queryParam.setLineName(lineName);
		return transformerInfoServiceImpl.getData(queryParam, new QcRowBounds());
	}

	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/add")
	public Json add(final TransformerInfo transformerInfo) {

		return handlerException(new HandlerAction() {
			@Override
			public void handler() throws Exception {
				List<TransformerInfo> list = new ArrayList<>();
				list.add(transformerInfo);
				transformerInfoServiceImpl.addList(list);
			}
		});
	}


	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/del")
	public Json del(final Integer id) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() throws Exception {
				transformerInfoServiceImpl.del(id);
			}
		});
	}

	@StringResult("权限不足")
	@RequestMapping("/maintainTransformer")
	public String maintainTransformerView() {
		return "transformer/maintainTransformer";
	}
}

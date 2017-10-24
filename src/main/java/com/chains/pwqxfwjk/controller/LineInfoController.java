package com.chains.pwqxfwjk.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chains.core.util.JsonResponse;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.LineInfoFor10kV;
import com.chains.pwqxfwjk.model.LineInfoFor35kV;
import com.chains.pwqxfwjk.service.LineInfoService;
import com.chains.pwqxfwjk.util.LineInfoQueryParam;
import com.chains.pwqxfwjk.util.Exception.ExistsSameNameException;
import com.chains.util.Json;
import com.chains.util.QcRowBounds;

@Controller
@RequestMapping("/lineInfo")
@RemoteProxy(name="LineInfoController")
public class LineInfoController {

	@Autowired
	private LineInfoService lineInfoServiceImpl;

	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/with35kvForJson")
	public List<LineInfoFor35kV> with35kvForJson(HttpServletRequest request, LineInfoQueryParam queryParam, QcRowBounds rowBounds) throws UnsupportedEncodingException {
		List<LineInfoFor35kV> list = lineInfoServiceImpl.get35kV(queryParam, rowBounds);
		return list;
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/with10kvForJson")
	public List<LineInfoFor10kV> with10kvForJson(HttpServletRequest request, LineInfoQueryParam queryParam, QcRowBounds rowBounds) {
		List<LineInfoFor10kV> list = lineInfoServiceImpl.get10kV(queryParam, rowBounds);
		return list;
	}
	
	@StringResult("权限不足")
	@RemoteMethod
	@ResponseBody
	@RequestMapping("/addVirtualLine")
	public Json addVirtualLine(List<LineInfoFor10kV> line) {
		try {
			lineInfoServiceImpl.addVirtualLine(line);
		}catch (ExistsSameNameException ee) {
			ee.printStackTrace();
			return JsonResponse.operationFailure(line.get(0).getBelongLine() + "线路下相同的地线名已存在", null);
		}catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.operationFailure();
		}
		return JsonResponse.operationSuccess();
	}
	
	@StringResult("权限不足")
	@RemoteMethod
	@ResponseBody
	@RequestMapping("/updateVirtualLine")
	public Json updateVirtualLine(List<LineInfoFor10kV> line) {
		try {
			lineInfoServiceImpl.updateLineInfo(line);
		}catch (ExistsSameNameException ee) {
			ee.printStackTrace();
			return JsonResponse.operationFailure(line.get(0).getBelongLine() + "线路下相同的地线名已存在");
		}catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.operationFailure();
		}
		return JsonResponse.operationSuccess();
	}
	
	@StringResult("权限不足")
	@RemoteMethod
	@ResponseBody
	@RequestMapping("/removeLineInfo")
	public Json removeLineInfo(String lineName, String branchName) {
		try {
			lineInfoServiceImpl.removeLineInfo(lineName, branchName);
		}catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.operationFailure();
		}
		return JsonResponse.operationSuccess();
	}
}

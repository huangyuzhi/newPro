package com.chains.pwqxfwjk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chains.core.util.ControllerSupport;
import com.chains.core.util.HandlerAction;
import com.chains.core.util.JsonResponse;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.service.TroubleLineService;
import com.chains.util.Json;

@Controller
@RequestMapping("/troubleLine")
public class TroubleLineController extends ControllerSupport {
	@Autowired
	private TroubleLineService troubleLineService;
	
	@StringResult("权限不足")
	@RequestMapping("/add")
	@ResponseBody
	public Json add(final TroubleLine troubleLine) {
		try {
			troubleLineService.addTroubleLine(troubleLine);
			Json response = JsonResponse.operationSuccess();
			response.setObj(troubleLine.getId());
			return response;
		}catch(IllegalArgumentException ie) {
			ie.printStackTrace();
			return JsonResponse.operationFailure(ie.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.operationFailure("系统错误,操作失败", null);
		}
	}
	
	@StringResult("权限不足")
	@RequestMapping("/del")
	@ResponseBody
	public Json del(final String lineQuality) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() {
				troubleLineService.delTroubleLine(lineQuality);
			}
		});
	}
	
	@StringResult("权限不足")
	@RequestMapping("/getAll")
	@ResponseBody
	public List<TroubleLine> getAll(final String ids) {
		return troubleLineService.getTroubleLines();
	}
}

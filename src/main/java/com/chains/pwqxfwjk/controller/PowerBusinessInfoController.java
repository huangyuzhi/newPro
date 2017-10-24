package com.chains.pwqxfwjk.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chains.pwqxfwjk.model.Ghsq;
import com.chains.pwqxfwjk.model.Glsq;
import com.chains.pwqxfwjk.model.PowerBusinessInfo;
import com.chains.pwqxfwjk.model.Xhsq;
import com.chains.pwqxfwjk.model.Xzsq;
import com.chains.pwqxfwjk.model.Ybsq;
import com.chains.pwqxfwjk.model.Zcsq;
import com.chains.pwqxfwjk.model.Zrsq;
import com.chains.pwqxfwjk.service.PowerBusinessInfoService;
import com.chains.pwqxfwjk.util.ConvertBean;
import com.chains.util.MapVo;

@Controller
@RequestMapping("/powerBusinessInfo")
public class PowerBusinessInfoController {
	@Autowired
	private PowerBusinessInfoService powerBusinessInfoServiceImpl;
	
	/**
	 * 方法名称:addPowerBusiness<br>
	 * 方法描述:                     <br>
	 * @param mapVo
	 * @return
	 * 返回类型:
	 * Json
	 * @exception
	*/
	@RequestMapping("/add")
	public String addPowerBusiness(MapVo mapVo, HttpServletRequest request) {
		PowerBusinessInfo powerBusinessInfo = null;
		Map<String, Object> map = mapVo.getMapVo();
		String businessType = (String) map.get("businessType");
		if(businessType.equals("xzsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Xzsq.class);
		}else if(businessType.equals("zrsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Zrsq.class);
		}else if(businessType.equals("ghsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Ghsq.class);
		}else if(businessType.equals("ybsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Ybsq.class);
		}else if(businessType.equals("glsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Glsq.class);
		}else if(businessType.equals("zcsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Zcsq.class);
		}else if(businessType.equals("xhsq")) {
			powerBusinessInfo = ConvertBean.convertFrom(map, Xhsq.class);
		}else {
			throw new RuntimeException("指定的业务类型错误");
		}
		powerBusinessInfoServiceImpl.addPowerBusiness(powerBusinessInfo);
		return "electricityBusiness/success";
	}
	
	@RequestMapping(value = "/{viewName}", method = RequestMethod.GET)
	public String panTo(@PathVariable("viewName") String viewName, HttpServletRequest request) {
		return "electricityBusiness/formPage/" + viewName;
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management() {
		return "electricityBusiness/Businessmanagement";
	}
}

package com.chains.weixin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chains.util.JSONUtils;
import com.chains.util.Json;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;

@Controller
@RequestMapping("/wx")
public class WxMenuController {
	
	 private static final Logger LOG  = LoggerFactory.getLogger(WxMenuController.class);
	@Autowired
	private ApiConfig apiConfig;
		
	@ResponseBody
	@RequestMapping("/getMenu")
	public Json getMenu(){
		Json json =new Json();
		MenuAPI mapi=new MenuAPI(apiConfig);
		List<MenuButton> button=new ArrayList<MenuButton>();
		
		MenuButton btn1=new MenuButton();
		btn1.setName("客户沟通");
		btn1.setSubButton(subButton());
		
		MenuButton btn2=new MenuButton();
		btn2.setName("业务办理");
		btn2.setKey(UUID.randomUUID().toString());
		btn2.setType(MenuType.VIEW);
		btn2.setUrl("http://www.lanyxge.com/newPro/powerBusinessInfo/management.do");
		
		MenuButton btn3 = new MenuButton();
		btn3.setName("近期停电通知");
		btn3.setType(MenuType.CLICK);
		btn3.setKey("notification");
		
		button.add(btn1);
		button.add(btn2);
		button.add(btn3);
		
		Menu meun=new Menu();
		meun.setButton(button);
		ResultType re= mapi.createMenu(meun);
		LOG.debug("创建菜单请求的响应： " + JSONUtils.toJSONString(re));
		json.setObj(mapi.getMenu().getMenu().toJsonString());
		return json;
	}
	
	private List<MenuButton> subButton() {
		List<MenuButton> subBottons = new ArrayList<MenuButton>();
		MenuButton button = new MenuButton();
		button.setName("投诉与建议");
		button.setType(MenuType.CLICK);
		button.setKey("suggest");
		
		MenuButton chgPhoneNumBtn = new MenuButton();
		chgPhoneNumBtn.setName("更改电话号码");
		chgPhoneNumBtn.setType(MenuType.CLICK);
		chgPhoneNumBtn.setKey("chgPhoneNum");
		
		subBottons.add(button);
		subBottons.add(chgPhoneNumBtn);
		return subBottons;
	}
}

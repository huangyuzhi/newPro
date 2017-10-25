package com.chains.pwqxfwjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chains.core.util.StringResult;

@Controller
public class ViewController {
	
	//地图主页面地址
	@StringResult("权限不足")
	@RequestMapping(value = "/main")
	public String toMain() {
		return "main";
	}
	
	@StringResult("权限不足")
	@RequestMapping(value = "/mapInterface")
	public String mapImprove() {
		return "mapInterface";
	}
	
	@StringResult("权限不足")
	@RequestMapping(value = "/mapInterface2")
	public String mapImprove2() {
		return "mapInterface3";
	}
}

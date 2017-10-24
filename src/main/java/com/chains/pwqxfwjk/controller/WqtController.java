package com.chains.pwqxfwjk.controller;

import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.service.WqtMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/WqtController")
public class WqtController  {
    @Autowired
    private WqtMissionService wqtMissionService;

    @RequestMapping("/getByMissonid")
    @ResponseBody
    public WqtMission getByMissonid(String missonId) {
        return wqtMissionService.getByMissonId(missonId);
    }
}

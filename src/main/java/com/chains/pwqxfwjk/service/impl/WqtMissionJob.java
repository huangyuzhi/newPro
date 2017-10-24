package com.chains.pwqxfwjk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.service.TroubleLineService;
import com.chains.pwqxfwjk.service.WqtMissionService;

@Component("wqtMissionJob")
public class WqtMissionJob {
	private Logger log = Logger.getLogger(WqtMissionJob.class);
	
	@Autowired
	private WqtMissionService wqtMissionServiceimpl;
	
	@Autowired
	private TransformerInfoService transformerInfoServiceImpl;
	
	@Autowired
	private TroubleLineService troubleLineServiceImpl;
	
	@Scheduled(cron = "0 0/1 * * * ?")    
	public void updateMission() { 
		log.info("更新外勤通任务信息");
		WqtMission mission = new WqtMission();
		mission.setStatus(2);	//进行中的任务
		List<WqtMission> list = wqtMissionServiceimpl.findByExample(mission);
		for(WqtMission wqtMission : list) {
			if((new Date()).compareTo(wqtMission.getSetTime()) < 0) { //未超时则更新
				List<WqtBackfill> backfills = wqtMissionServiceimpl.findBackfill(wqtMission.getMissionId());
				if(backfills.size() > 0) {
					WqtBackfill backfill = backfills.get(0);
					try {
						if(backfill.getStatus().equals(3) ) {	//已完成
							if(wqtMission.getDestinationDeviceType().equals("transformer")) {
								transformerInfoServiceImpl.changeFault(wqtMission.getDestinationKey(), false);
							}
							if(wqtMission.getDestinationDeviceType().equals("line")) {
								List<Integer> ids = new ArrayList<>();
								ids.add(wqtMission.getDestinationKey());
								troubleLineServiceImpl.delTroubleLine(ids);
							}
							log.info("更新任务编号" + wqtMission.getMissionId() +"  回填数据" + JSON.toJSONString(backfill));
							wqtMissionServiceimpl.backfillMission(backfill, wqtMission.getMissionId());
						}else {
							log.info("任务编号" + wqtMission.getMissionId() + " 数据" + JSON.toJSONString(backfill) + " 未回填");
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.info(e.getMessage());
					}
				}
			}
		}
	}
	
}

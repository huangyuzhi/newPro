package com.chains.pwqxfwjk.other;

import java.util.Date;

import com.chains.pwqxfwjk.model.WqtMission;

/**
 * 类名称:MissionAdapter<br>
 * 功能描述: 外勤通任务适配                      <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2016年4月6日 上午11:36:47<br>
 * 修改人:zw<br>
 * 修改时间:2016年4月6日 上午11:36:47<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class MissionAdapter {
	
	private WqtMission mission;
	public MissionAdapter(WqtMission mission) {
		this.mission = mission;
	}
	
	public String getExeUser() {
		return mission.getExeUser();
	}
	
	public Date getSetTime() {
		return mission.getSetTime();
	}
	
	public String getTitle() {
		return mission.getTitle();
	}
	
	public String getIsHave() {
		return mission.getIsHave();
	}
	
	public String getContent() {
		String result = "";
		String consumeNamePart = mission.getConsumeName() != null ? "\n 客户姓名： " + mission.getConsumeName() : "";
		String consumePhonePart = mission.getConsumePhone() != null ? "\n  客户电话：" + mission.getConsumePhone() : "";
		result += mission.getContent() + consumeNamePart + consumePhonePart;
		return  result;
	}
}

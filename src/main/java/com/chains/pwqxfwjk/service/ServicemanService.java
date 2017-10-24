package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.PersonInfo;
import com.chains.pwqxfwjk.model.Serviceman;

public interface ServicemanService {
	
	public void assignMission(Serviceman serviceman, String missionId);

	public void assignMission(List<String> wqtIds, String missionId);

	public List<Serviceman> findByExample(Serviceman serviceman);

	/**
	 * 返回正在执行任务的人员
	 * @return List<Serviceman>
	 */
	public List<Serviceman> findExecuteServiceman();


	/**
	 * 临时方法，填充personInfo
	 * @param personInfos
	 */
	void fillPersonInfo(List<PersonInfo> personInfos);
}

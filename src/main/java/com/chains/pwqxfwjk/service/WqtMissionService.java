package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.WqtBackfill;

public interface WqtMissionService {

	/**
	 * 
	 * @param mission
	 * @return String 任务工单的id值。
	 * @throws Exception
	 */
	String assignMission(WqtMission mission) throws Exception;

	/**
	 * 回填任务
	 * @param wqtBackfill
	 * @param missionId
	 */
	void backfillMission(WqtBackfill wqtBackfill, String missionId);
	
	public List<WqtMission> findByExample(WqtMission wqtMission);

	/**
	 * 查询回填信息
	 * @param ids
	 * @return
	 */
	public List<WqtBackfill> findBackfill(String ids);

	WqtMission getByMissonId(String missonId);
}
package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.FaultStaticViewModel;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.util.FaultStatisticsDetailQueryParam;
import com.chains.util.QcRowBounds;

public interface FaultStatisticsDetailService {
	public List<FaultStatisticsDetail> find(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds);
	
	public Long count(FaultStatisticsDetail faultStatisticsDetail);
	
	public void add(FaultStatisticsDetail faultStatisticsDetail );
	
	public String assignMissionAndStatistics(WqtMission wqtMission, FaultStatisticsDetail faultStatisticsDetail, TroubleLine troubleLine) throws Exception;
	
	public void backfill(FaultStatisticsDetail faultStatisticsDetail);

	public List<FaultStatisticsDetail> find(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds,
			FaultStatisticsDetailQueryParam queryParam);

	public void backfillMission(WqtBackfill wqtBackfill, String missionId);

	public void del(Integer id);

	public Long count(FaultStatisticsDetail faultStatisticsDetail, FaultStatisticsDetailQueryParam queryParam);

	public byte[] staticExcel(Integer year, Integer month, List<String> groupFields);

	public List<FaultStaticViewModel> viewModel(Integer year, Integer month, List<String> groupFields);
	
}

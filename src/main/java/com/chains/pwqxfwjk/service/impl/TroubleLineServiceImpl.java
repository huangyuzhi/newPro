package com.chains.pwqxfwjk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.service.TroubleLineService;

@Service
public class TroubleLineServiceImpl implements TroubleLineService {
	
	@Autowired
	private BaseDaoI<TroubleLine> baseDaoI;
	
	@Override
	public Integer addTroubleLine(TroubleLine troubleLine) {
		return (Integer) baseDaoI.add(troubleLine);
	}

	@Override
	public List<TroubleLine> getTroubleLines() {
		return baseDaoI.getList("from TroubleLine");
	}

	@Override
	public void delTroubleLine(List<Integer> uniqueKeys) {
		Assert.isTrue(uniqueKeys.size() > 0, "必须指定要删除的故障线路的id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", uniqueKeys);
		baseDaoI.del("delete TroubleLine where id in (:ids)", params);
	}

	@Override
	public void delTroubleLine(String lineQuality) {
		Assert.isTrue(lineQuality != null && lineQuality.length() > 0, "参数不能为空");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lineQuality", lineQuality);
		baseDaoI.del("delete TroubleLine where lineQuality = :lineQuality", params);
	}
}

package com.chains.pwqxfwjk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.SubstationInfo;
import com.chains.pwqxfwjk.service.SubstationInfoService;
import com.chains.pwqxfwjk.util.SubstationInfoQueryParam;
import com.chains.util.QcRowBounds;

@Service("SubstationInfoServiceImpl")
public class SubstationInfoServiceImpl implements SubstationInfoService {
	@Autowired
	private BaseDaoI<SubstationInfo> baseDaoI;

	public List<SubstationInfo> getData(SubstationInfoQueryParam queryParam,
			QcRowBounds rowBounds) {
		String hql = "from SubstationInfo l order by l.id ";
		return baseDaoI.getList(hql);
	}
}

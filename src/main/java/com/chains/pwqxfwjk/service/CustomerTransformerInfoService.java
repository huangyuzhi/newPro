package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.CustomerTransformerInfo;

public interface CustomerTransformerInfoService {
	void insertTable(String sql);
	
	List<CustomerTransformerInfo> getData(String costomerNumber);
	
	CustomerTransformerInfo getByCostomerNumber(String costomerNumber);
	
	List<CustomerTransformerInfo> getByCostomerName(String costomerName);
	
	void addList(List<CustomerTransformerInfo> list);

	void init();

	/**
	 * 清理数据
	 */
	/*void cleanData();*/
}

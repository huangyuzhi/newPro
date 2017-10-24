package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.PowerBusinessInfo;

public interface PowerBusinessInfoService {
	public <T extends PowerBusinessInfo>List<T> getPowerBusiness(Class<T> powerBusinessClz);
	
	/**
	 * 方法名称:addPowerBusiness<br>
	 * 方法描述:  添加一个申请表                   <br>
	 * @param powerBusinessInfo
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void addPowerBusiness(PowerBusinessInfo powerBusinessInfo);
}

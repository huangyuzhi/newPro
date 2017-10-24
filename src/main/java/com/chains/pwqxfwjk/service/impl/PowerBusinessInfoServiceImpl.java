package com.chains.pwqxfwjk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.PowerBusinessInfo;
import com.chains.pwqxfwjk.service.PowerBusinessInfoService;

@Service("powerBusinessInfoServiceImpl")
public class PowerBusinessInfoServiceImpl implements PowerBusinessInfoService {
	@Autowired
	private BaseDaoI<PowerBusinessInfo> baseDaoIWithPwoerBus;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDaoI baseDaoI;
	
	@Override
	public void addPowerBusiness(PowerBusinessInfo powerBusinessInfo) {
		baseDaoIWithPwoerBus.add(powerBusinessInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends PowerBusinessInfo> List<T> getPowerBusiness(
			Class<T> powerBusinessClz) {
		String clazName = powerBusinessClz.getSimpleName();
		String hql = "" ;
		Map<String, Object> params = new HashMap<String, Object>();
		String discriminatorValue = null;
		try {
			discriminatorValue = powerBusinessClz.newInstance().getBusinessType();
			if(clazName.equals("PowerBusinessInfo")) {
				throw new RuntimeException("类型错误");
			}else {
				hql += "select p from " + clazName + " p where p.businessType = :discriminatorValue";
				
				params.put("discriminatorValue", discriminatorValue);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return baseDaoI.getList(hql, params);
	}

}

package com.chains.pwqxfwjk.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.PowerInterruptionNotificationInfo;
import com.chains.pwqxfwjk.service.PowerInterruptionNotificationService;

@Service
public class PowerInterruptionNotificationServiceImpl implements
		PowerInterruptionNotificationService {
	@Autowired
	private BaseDaoI<PowerInterruptionNotificationInfo> baseDaoI;
	
	@Override	
	public void publishNotification(PowerInterruptionNotificationInfo info) {
		baseDaoI.add(info);
	}

	@Override
	public List<PowerInterruptionNotificationInfo> getRecentNotification(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		Date date = calendar.getTime();
		String hql = "from PowerInterruptionNotificationInfo p where p.releaseTime > :date";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		return baseDaoI.getList(hql, params);
	}
}

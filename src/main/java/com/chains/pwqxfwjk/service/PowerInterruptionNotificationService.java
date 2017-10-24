package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.PowerInterruptionNotificationInfo;

public interface PowerInterruptionNotificationService {
	/**
	 * 方法名称:publishNotification<br>
	 * 方法描述: 发布停电公告                    <br>
	 * @param info
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void publishNotification(PowerInterruptionNotificationInfo info);
	
	/**
	 * 方法名称:getRecentNotification<br>
	 * 方法描述: 获取近期days天的通知                    <br>
	 * 返回类型:
	 * void
	 * @exception
	*/
	public List<PowerInterruptionNotificationInfo> getRecentNotification(int days);
}

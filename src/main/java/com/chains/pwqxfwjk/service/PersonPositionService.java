package com.chains.pwqxfwjk.service;

import com.chains.pwqxfwjk.model.PersonInfoCollection;
import com.chains.pwqxfwjk.util.PersonPositionQueryParam;

public interface PersonPositionService {
	/**
	 * 方法名称:getPersonPosition<br>
	 * 方法描述:      获取人员的位置和个人信息                  <br>
	 * @param param
	 * @return
	 * 返回类型:
	 * PersonInfoCollection
	 * @exception
	*/
	public PersonInfoCollection getPersonPosition(PersonPositionQueryParam param);
	
}

package com.chains.pwqxfwjk.service;

import java.util.List;
import java.util.Map;

import com.chains.pwqxfwjk.model.CustomerChangePhoneInfo;
import com.chains.pwqxfwjk.model.CustomerComplianInfo;
import com.chains.pwqxfwjk.model.CustomerInfo;
import com.chains.pwqxfwjk.model.VCustomerInfo;
import com.chains.pwqxfwjk.util.CustomerInfoQueryParam;
import com.chains.util.Page;
import com.chains.util.QcRowBounds;

public interface CustomerInfoService {
	public void addList(List<CustomerInfo> customers);
	
	/**
	 * 方法名称:changePhoneNumber<br>
	 * 方法描述: 改变电话号码                    <br>
	 * @param info
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void changePhoneNumber(CustomerChangePhoneInfo info);
	
	public void customerComplianInfo(CustomerComplianInfo info);
	
	public List<CustomerInfo> findByExample(CustomerInfo customerInfo);

	List<CustomerInfo> findByCustomerNumber(String customerNumber);
	/**
	 * 方法名称:getCustomerV<br>
	 * 方法描述:  获得视图信息                   <br>
	 * @return
	 * 返回类型:
	 * List<VCustomerInfo>
	 * @exception
	*/
	public Page<VCustomerInfo> getCustomerV(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds);
	
	public List<VCustomerInfo> getCustomerVList(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds);
	
	public void add(CustomerInfo customerInfo);
	
	/**
	 * 方法名称:del<br>
	 * 方法描述: 根据指定id删除对象                <br>
	 * @param ids
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void del(List<Integer> ids);
	
	public void update(CustomerInfo ci);
	/**
	 * 方法名称:getCodeMapping<br>
	 * 方法描述: 获得编码信息                    <br>
	 * @param codeType
	 * @return
	 * 返回类型:
	 * Map<Integer,String>
	 * @exception
	*/
	public List<Map<String, Object>> getCodeMapping(String codeType);
	
}

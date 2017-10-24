package com.chains.pwqxfwjk.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chains.pwqxfwjk.model.VCustomerInfo;

public class CustomerInfoQueryParam extends QueryParam {
	private String customerNumber;
	private Date beginDate;
	private Date endDate;
	private String hql;
	private Map<String, Object> params = new HashMap<String, Object>();
	private Map<String, Object> countparams = new HashMap<String, Object>();
	
	/**
	 * @since customerNumber
	 * @return String
	 */
	
	public String getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @since customerNumber
	 * @param customerNumber
	 * @return void
	 */
	
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public String getHql(Class<?> clz) {
		String hql = "";
		if(getSelectField() != null && getSelectField().trim().length() > 0) {
			hql += "select " + getSelectField() + " ";
		}
		if(VCustomerInfo.class.equals(clz)) {
			hql += " from VCustomerInfo where 1 = 1 ";
		}
		if(getCustomerNumber() != null && getCustomerNumber().trim().length() > 0) {
			hql += "and customerNumber = :customerNumber ";
			params.put("customerNumber", getCustomerNumber());
		}
		if(isSort()) {
			hql += "order by " + getSortedBy() + " " + getOrder();
			
		}
		return hql;
	}
	
	public String getCountHql(Class<?> clz) {
//		String hql = " select count(e) from " + clz.getName() + " e where 1 = 1 " ;
		String hql = " select count(e) from CustomerInfo e where 1 = 1 " ;
		if(getCustomerNumber() != null && getCustomerNumber().trim().length() > 0) {
			hql += " and e.customerNumber like :customerNumber ";
			countparams.put("customerNumber", getCustomerNumber());
		}
		if(getBeginDate() != null) {
			hql += " and e.lastUpdateTime between :beginDate and :endDate ";
			countparams.put("beginDate", getBeginDate());
			countparams.put("endDate", getEndDate());
		}
		return hql;
	}
	/**
	 * @since params
	 * @return Map<String,Object>
	 */
	
	public Map<String, Object> getParams() {
		return params;
	}
	/**
	 * @since countparams
	 * @return Map<String,Object>
	 */
	
	public Map<String, Object> getCountparams() {
		return countparams;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String getSortedBy() {
		if(super.getSortedBy() != null && super.getSortedBy().equals("customerNumber")) {
			return "customer_number";
		}
		return super.getSortedBy();
	}
}

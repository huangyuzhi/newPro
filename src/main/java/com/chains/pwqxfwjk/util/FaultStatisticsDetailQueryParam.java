package com.chains.pwqxfwjk.util;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class FaultStatisticsDetailQueryParam extends QueryParam {
	private Date beginDate;
	private Date endDate;
	
	public Criteria addRestriction(Criteria criteria) {
		if(beginDate != null && endDate != null) {
			criteria.add(Restrictions.between("sendMissionTime", beginDate, endDate));
		}
		return criteria;
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
	
	
}

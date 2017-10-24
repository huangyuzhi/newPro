package com.chains.pwqxfwjk.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chains.pwqxfwjk.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.service.CustomerInfoService;
import com.chains.pwqxfwjk.util.CustomerInfoQueryParam;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.chains.util.Page;
import com.chains.util.QcRowBounds;

@Service("customerInfoServiceImpl")
public class CustomerInfoServiceImpl implements CustomerInfoService {
	@Autowired
	private BaseDaoI<CustomerInfo> baseDaoI;
	@Autowired
	private BaseDaoI<Object> baseDaoIComm;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addList(List<CustomerInfo> customers) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		hibernateUtil.addList(customers, 100);
	}

	@Override
	public void changePhoneNumber(CustomerChangePhoneInfo info) {
		try {
			baseDaoIComm.save(info);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void customerComplianInfo(CustomerComplianInfo info) {
		try {
			baseDaoIComm.save(info);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<VCustomerInfo> getCustomerVList(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Session session = hibernateUtil.getCurrentSession();
		@SuppressWarnings("JpaQueryApiInspection") Query query = session.getNamedQuery("findCustomerInfoV");
		String exampleSqlString = query.getQueryString() + " where 1 = 1 ";
		Map<String, Object> params = new HashMap<>();
		if(customerInfoQueryParam.getCustomerNumber() != null && customerInfoQueryParam.getCustomerNumber().trim().length() > 0) {
			exampleSqlString += " and CUSTOMER_NUMBER = :customerNumber ";
			params.put("customerNumber", customerInfoQueryParam.getCustomerNumber());
		}
		if(customerInfoQueryParam.getBeginDate() != null) {
			exampleSqlString += " and LAST_UPDATE_TIME between :beginDate and :endDate ";
			params.put("beginDate", customerInfoQueryParam.getBeginDate());
			params.put("endDate", customerInfoQueryParam.getEndDate());
		}
		if(customerInfoQueryParam.isSort()) {
			String sortClause = " order by " + customerInfoQueryParam.getSortedBy() + " " + customerInfoQueryParam.getOrder();
			exampleSqlString += sortClause;
		}
		return hibernateUtil.executeSql(exampleSqlString, params, rowBounds).setResultSetMapping("VCustomerInfo").list();
	}

	@Override
	public Page<VCustomerInfo> getCustomerV(CustomerInfoQueryParam customerInfoQueryParam, QcRowBounds rowBounds) {
		List<VCustomerInfo> customers = getCustomerVList(customerInfoQueryParam, rowBounds);
		long totalRows = baseDaoI.getCount(customerInfoQueryParam.getCountHql(VCustomerInfo.class), customerInfoQueryParam.getCountparams());
		Page<VCustomerInfo> page = new Page<>();
		page.setTotal((int)totalRows);
		page.setList(customers);
		return page;
	}

	@Override
	public void add(CustomerInfo customerInfo) {
		customerInfo.setLastUpdateTime(new Date());
		baseDaoI.add(customerInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCodeMapping(String codeType) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select * from CODE_MAPPING cm where cm.codeType = :codeType";
		params.put("codeType", codeType);
		SQLQuery sqlQuery = hibernateUtil.executeSql(sql, params);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public void del(List<Integer> ids) {
		Assert.isTrue(ids != null && ids.size() > 0, "参数无效");
		String hql = "delete CustomerInfo c where c.id in (:ids)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		baseDaoI.del(hql, params);
	}

	@Override
	public void update(CustomerInfo ci) {
		try {
			CustomerInfo persistentCustomerInfo = baseDaoI.getById(CustomerInfo.class, ci.getId());
			String updateField = "";
			BeanInfo beanInfo = Introspector.getBeanInfo(CustomerInfo.class); 
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
				 PropertyDescriptor descriptor = propertyDescriptors[i]; 
				 String propertyName = descriptor.getName(); 
				 if (!propertyName.equals("class") && !propertyName.equals("updateField")) { 
			        Method readMethod = descriptor.getReadMethod(),
			        	   writeMethod = descriptor.getWriteMethod(); 
			        Object result = readMethod.invoke(ci, new Object[0]); 
			        Object persistentResult = readMethod.invoke(persistentCustomerInfo, new Object[0]);
			        if(result == null && persistentResult != null) {
			        	writeMethod.invoke(persistentCustomerInfo, result);
			        	updateField += propertyName + ",";
			        }
			        if(result != null && !result.equals(persistentResult)) {
			        	writeMethod.invoke(persistentCustomerInfo, result);
			        	updateField += propertyName + ",";
			        }
				 }    
			}
			persistentCustomerInfo.setLastUpdateTime(new Date());
			persistentCustomerInfo.setUpdateField(updateField.substring(0, updateField.length() - 1));
			ci.setLastUpdateTime(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerInfo> findByExample(CustomerInfo customerInfo) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Criteria criteria = hibernateUtil.exampleQuery(customerInfo);
		return criteria.list();
	}

	@Override
	public List<CustomerInfo> findByCustomerNumber(String customerNumber) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		String hql = "select distinct customerNumber as customerNumber from CustomerInfo l where l.customerNumber like :customerNumber ";
		Map<String, Object> params = new HashMap<>();
		params.put("customerNumber", customerNumber + "%");
		Query query = hibernateUtil.executeHql(hql, params, new QcRowBounds(0, 5));
		return query.setResultTransformer(Transformers.aliasToBean(CustomerInfo.class)).list();
	}
}

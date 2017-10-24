package com.chains.pwqxfwjk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chains.pwqxfwjk.model.TransformerInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.CustomerTransformerInfo;
import com.chains.pwqxfwjk.service.CustomerTransformerInfoService;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.chains.util.QcRowBounds;

@Service("CustomerTransformerInfoServiceImpl")
public class CustomerTransformerInfoServiceImpl implements CustomerTransformerInfoService  {

	@Autowired
	private BaseDaoI<CustomerTransformerInfo> baseDaoI;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertTable(String sql) {
		try {
			baseDaoI.deleteBySql(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<CustomerTransformerInfo> getData(String costomerNumber) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		String hql = "select distinct customerNumber as customerNumber from CustomerTransformerInfo l where l.customerNumber like :costomerNumber ";
		Map<String, Object> params = new HashMap<>();
		params.put("costomerNumber", costomerNumber + "%");
		Query query = hibernateUtil.executeHql(hql, params, new QcRowBounds(0, 5));
		return query.setResultTransformer(Transformers.aliasToBean(CustomerTransformerInfo.class)).list();
	}
	
	public CustomerTransformerInfo getByCostomerNumber(String costomerNumber) {
		String hql = "from CustomerTransformerInfo l where l.customerNumber = :costomerNumber ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("costomerNumber", costomerNumber);
		return baseDaoI.getModel(hql, params);
	}
	@Override
	public List<CustomerTransformerInfo> getByCostomerName(String costomerName) {
		String hql = "from CustomerTransformerInfo l where l.customerName = :customerName ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", costomerName);
		return baseDaoI.getList(hql, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomerTransformerInfo> findByExample(CustomerTransformerInfo param) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CustomerTransformerInfo.class).add(Example.create(param));
		return criteria.list();
	}
	@Override
	public void addList(List<CustomerTransformerInfo> list) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		hibernateUtil.addList(list, 20);
	}
	
	@Override
	public void init() {
		List<CustomerTransformerInfo> list = findByExample(new CustomerTransformerInfo());
		for (CustomerTransformerInfo customerTransformerInfo : list) {
			String transformerName = customerTransformerInfo.getTransformerName();
		}
	}

/*	@Override
	@SuppressWarnings(value = "unchecked")
	public void cleanData() {
		Session session = sessionFactory.openSession();
		List<String> transformerNames = session
								.createQuery(
										"select distinct transformerName from CustomerTransformerInfo ct " +
										"where not exists (" +
										"select 1 from TransformerInfo t " +
										"where t.transformerName = ct.transformerName)").list();
		for (String transformerName :
				transformerNames) {
			Pattern pattern = Pattern.compile("(\\d+-?\\d*号?)");
			Matcher matcher = pattern.matcher(transformerName);
			String queryParam ;
			if(matcher.find()) {
				queryParam = matcher.group();
				List<TransformerInfo> list = session.createSQLQuery("SELECT * from TRANSFORMER_INFO where TRANSFORMER_NAME REGEXP  '^"  + queryParam + "'")
						.addEntity(TransformerInfo.class).list();

				for (int i = 0; i < list.size(); i++) {
					TransformerInfo transformer = list.get(i);
					if (!transformer.getTransformerName().contains("东风")) {
						session.createQuery("update CustomerTransformerInfo set transformerName = '" + transformer.getTransformerName() + "'" +
								" where transformerName = '" + transformerName + "'").executeUpdate();
						break;
					}
				}
			}
		}
	}*/
}

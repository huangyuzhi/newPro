package com.chains.pwqxfwjk.util;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.util.QcRowBounds;


public class HibernateUtil {
	
	private SessionFactory sessionFactory;
	public HibernateUtil(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Query executeHql(String hql, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		setParam(query, params);
		return query;
	}
	
	public SQLQuery executeSql(String sql, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		setParam(sqlQuery, params);
		return sqlQuery;
	}
	
	public SQLQuery executeSql(String sql, Map<String, Object> params, QcRowBounds rowBounds) {
		return (SQLQuery) executeSql(sql, params).setFirstResult(rowBounds.getOffset()).setMaxResults(rowBounds.getLimit());
	}
	
	public Query executeHql(String hql, Map<String, Object> params, QcRowBounds rowBounds) {
		return executeHql(hql, params).setFirstResult(rowBounds.getOffset()).setMaxResults(rowBounds.getLimit());
	}
	
	public <T>void addList(List<T> list, int intervalOfFlush) {
		addList(list, intervalOfFlush, null);
	}
	/**
	 * 方法名称:addList<br>
	 * 方法描述:  批量插入数据 ，该方法本身不进行事务处理。                  <br>
	 * @param list
	 * 返回类型:
	 * void
	 * @exception
	*/
	public <T>void addList(List<T> list, int intervalOfFlush, CallBack<T> callBack) {
		Session session = sessionFactory.getCurrentSession();
		int count = 0;
		for (T t : list) {
			session.save(t);
			if(callBack != null) {
				callBack.callBack(t);
			}
			count++;
			if(count % intervalOfFlush == 0) {
				session.flush();
				session.clear(); 
			}
		}
	}
	
	public <T>Criteria exampleQuery(T entity) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		try {
			criteria = session.createCriteria(entity.getClass()).add(Example.create(entity));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return criteria;
	}
	
	public <T>Criteria exampleQuery(T entity, QcRowBounds rowBounds) {
		Criteria criteria = exampleQuery(entity);
		criteria.setFirstResult(rowBounds.getOffset()).setMaxResults(rowBounds.getLimit());
		return criteria;
	}
	
	/**获取行统计信息，需要传入的entity是一个hibernate实体类。
	 * @param entity
	 * @return 
	 */
	public long countRows(Class<?> entity) {
		String hql = "select count(e) from " + entity.getName() + " e where 1 = 1 ";
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.createQuery(hql).uniqueResult();
	}
	
	@SuppressWarnings("rawtypes")
	private void setParam(Query query, Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key) instanceof Collection) {
					query.setParameterList(key, (Collection) params.get(key));
				} else {
					query.setParameter(key, params.get(key));
				}
			}
		}
	}
}

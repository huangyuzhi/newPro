package com.chains.pwqxfwjk.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.GPSCoords;
import com.chains.pwqxfwjk.model.LineInfoFor10kV;
import com.chains.pwqxfwjk.model.LineInfoFor35kV;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.service.LineInfoService;
import com.chains.pwqxfwjk.util.CallBack;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.chains.pwqxfwjk.util.LineInfoQueryParam;
import com.chains.pwqxfwjk.util.Exception.ExistsSameNameException;
import com.chains.util.QcRowBounds;

@Service("lineInfoServiceImpl")
public class LineInfoServiceImpl implements LineInfoService {
	
	protected final Logger LOGGER = Logger.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BaseDaoI<LineInfoFor10kV> baseDaoIFor10kV;
	
	@Autowired
	private BaseDaoI<LineInfoFor35kV> baseDaoIFor35kV;
	
	
	/**
	 * 方法名称:get10kV<br>
	 * 方法描述:重写方法                                                       <br>
	 * @param queryParam
	 * @param rowBounds
	 * @return
	 * 返回类型:
	 * 
	 * @exception
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LineInfoFor10kV> get10kV(LineInfoQueryParam queryParam, QcRowBounds rowBounds) {
		Assert.notNull(queryParam, "查询参数不能为null");
		Map<String, Object> params = new HashMap<String, Object>();
		String selectField = queryParam.getSelectField() != null ? queryParam.getSelectField() : "l";
		String hql = "select " + selectField + " from LineInfoFor10kV l where 1 = 1  ";
		if(queryParam.getBelongLine() != null && !"".equals(queryParam.getBelongLine().trim())) {
			hql += "and l.belongLine = :belongLine ";
			params.put("belongLine", queryParam.getBelongLine());
		}
		if(queryParam.getBelongBranch() != null && !"".equals(queryParam.getBelongBranch().trim())) {
			hql += "and l.belongBranch = :belongBranch ";
			params.put("belongBranch", queryParam.getBelongBranch());
		}
		if(queryParam.getId() != null ) {
			hql += "and l.id = :id ";
			params.put("id", queryParam.getId());
		}
		if(queryParam.isSort() && queryParam.getSortedBy() != null && !"".equals(queryParam.getSortedBy().trim())) {
			hql += "order by " + queryParam.getSortedBy();
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql).setFirstResult(rowBounds.getOffset()).setMaxResults(rowBounds.getLimit());
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key) instanceof Collection) {
					query.setParameterList(key, (Collection) params.get(key));
				} else {
					query.setParameter(key, params.get(key));
				}
			}
		}
		return selectField.equals("l") ? query.list() : query.setResultTransformer(Transformers.aliasToBean(LineInfoFor10kV.class)) .list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LineInfoFor35kV> get35kV(LineInfoQueryParam queryParam,  QcRowBounds rowBounds) {
		Assert.notNull(queryParam, "查询参数不能为null");
		Map<String, Object> params = new HashMap<String, Object>();
		String selectField = queryParam.getSelectField() != null ? queryParam.getSelectField() : "l";
		String hql = "select " + selectField + " from LineInfoFor35kV l where 1 = 1  ";
		if(queryParam.getBelongLine() != null && !"".equals(queryParam.getBelongLine().trim())) {
			hql += "and l.belongLine = :belongLine ";
			params.put("belongLine", queryParam.getBelongLine());
		}
		if(queryParam.getId() != null ) {
			hql += "and l.id = :id ";
			params.put("id", queryParam.getId());
		}
		if(queryParam.isSort() && queryParam.getSortedBy() != null && !"".equals(queryParam.getSortedBy().trim())) {
			hql += "order by " + queryParam.getSortedBy();
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql).setFirstResult(rowBounds.getOffset()).setMaxResults(rowBounds.getLimit());
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key) instanceof Collection) {
					query.setParameterList(key, (Collection) params.get(key));
				} else {
					query.setParameter(key, params.get(key));
				}
			}
		}
		return selectField.equals("l") ? query.list() : query.setResultTransformer(Transformers.aliasToBean(LineInfoFor35kV.class)) .list();
	}
	
	/**
	 * 方法名称:addVirtualLine<br>
	 * 方法描述:要插入的线路都是具有相同的线路名和支路名的                                                       <br>
	 * @param line
	 * 返回类型:
	 * 
	 * @exception
	*/
	public synchronized void addVirtualLine(List<LineInfoFor10kV> line) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Assert.isTrue(line.size() > 0, "参数不能是个空数组");
		
		List<LineInfoFor10kV> list = getLineInfoByNameAndBranch(line.get(0).getBelongLine(), line.get(0).getBelongBranch());
		if(list.size() > 0) {
			throw new ExistsSameNameException("名称已存在!");
		}
		hibernateUtil.addList(line, 10, new CallBack<LineInfoFor10kV>() {
			@Override
			public void callBack(LineInfoFor10kV t) {
				LOGGER.info("插入了id为" + t.getId() + "的值");
				t.setTowerSort((double) t.getId());
			}
		});
	}

	@Override
	public void removeLineInfo(String lineName, String branchName) {
		assertLineAndBranchNameIsNotEmpty(lineName, branchName);
		String delHql = "delete LineInfoFor10kV l where l.belongLine = :belongLine and l.belongBranch= :belongBranch ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("belongLine", lineName);
		params.put("belongBranch", branchName);
		baseDaoIFor10kV.del(delHql, params);
	}

	@Override
	public void updateLineInfo(List<LineInfoFor10kV> lines) {
		Assert.isTrue(lines.size() > 0, "参数不能是个空数组");
		removeLineInfo(lines.get(0).getBelongLine(), lines.get(0).getBelongBranch());
		addVirtualLine(lines);
	}
	
	public void insertTempTable(List<? extends GPSCoords> list, TransServiceModel model) throws Exception {
		String insertTempSql = "insert into temp (temp_id, bd_coords_x, bd_coords_y) values ";
		StringBuilder insertTempData = new StringBuilder();
		for(int i = 0; i < model.getResult().size(); i++) {
			insertTempData.append("(");
			insertTempData.append(list.get(i).getId() + ",");
			insertTempData.append(model.getResult().get(i).getX() + ",");
			insertTempData.append(model.getResult().get(i).getY() + "),");
		}
		baseDaoIFor10kV.deleteBySql(insertTempSql + insertTempData.substring(0, insertTempData.length() - 1));
	}
	
	/**
	 * 方法名称:AssertLineAndBranchNameIsNotEmpty<br>
	 * 方法描述: 断言lineName和branchName不为空                   <br>
	 * @param lineName
	 * @param branchName
	 * 返回类型:
	 * void
	 * @exception
	*/
	private void assertLineAndBranchNameIsNotEmpty(String lineName, String branchName) {
		Assert.isTrue(lineName != null && lineName.trim().length() > 0, "线路名不能为空");
		Assert.isTrue(branchName != null && branchName.trim().length() > 0, "支路名不能为空");
	}
	/**
	 * 方法名称:getLineInfoByNameAndBranch<br>
	 * 方法描述:  根据线路名和支路名查找线路                   <br>
	 * @param lineName
	 * @param branchName
	 * @return
	 * 返回类型:
	 * List<LineInfoFor10kV>
	 * @exception
	*/
	private List<LineInfoFor10kV> getLineInfoByNameAndBranch(String lineName, String branchName) {
		assertLineAndBranchNameIsNotEmpty(lineName, branchName);
		LineInfoQueryParam queryParam = new LineInfoQueryParam();
		queryParam.setBelongLine(lineName);
		queryParam.setBelongBranch(branchName);
		List<LineInfoFor10kV> list = get10kV(queryParam, new QcRowBounds());
		return list;
	}
}

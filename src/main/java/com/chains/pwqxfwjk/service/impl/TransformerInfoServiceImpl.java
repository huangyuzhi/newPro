package com.chains.pwqxfwjk.service.impl;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.other.BdCoord;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.service.CoordsConvertionService;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.util.QcRowBounds;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("TransformerInfoServiceImpl")
public class TransformerInfoServiceImpl implements TransformerInfoService {
	@Autowired
	private BaseDaoI<TransformerInfo> baseDaoI;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CoordsConvertionService coordsConvertionService;
	
	@SuppressWarnings("unchecked")
	public List<TransformerInfo> getData(TransformerInfoQueryParam queryParam,
			QcRowBounds rowBounds) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Query query = hibernateUtil.executeHql(queryParam.genenrateHql(), queryParam.getParams(), rowBounds);
		return queryParam.getSelectField() != null ? query.setResultTransformer(Transformers.aliasToBean(TransformerInfo.class)).list() : query.list();
	}
	
	public TransformerInfo getTransformerByUser(String customerNumber) {
		Assert.isTrue(customerNumber != null && !"".equals(customerNumber.trim()), "customerNumber不能为空");
		String hql = " select distinct t from TransformerInfo t, CustomerTransformerInfo ct where t.transformerName = ct.transformerName  ";
		Map<String, Object> params = new HashMap<>();
		hql += "and ct.customerNumber = :customerNumber ";
		params.put("customerNumber", customerNumber);
		return baseDaoI.getModel(hql, params);
	}

	public List<TransformerInfo> getTransformerByLine(String lineName) {
		Assert.isTrue(lineName != null && !"".equals(lineName.trim()), "参数不能为空");
		String hql = "select distinct t from TransformerInfo t, CustomerTransformerInfo ct where t.transformerName = ct.transformerName ";
		Map<String, Object> params = new HashMap<>();
		hql += "and ct.lineName = :lineName ";
		params.put("lineName", lineName);
		return baseDaoI.getList(hql, params);
	}

	@Override
	public Long count(TransformerInfoQueryParam queryParam) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Query query = hibernateUtil.executeHql(queryParam.genenrateCount(), queryParam.getParams());
		return (Long) query.uniqueResult();
	}

	public List<TransformerInfo> getTransformerByCustomerName(String customerName) {
		Assert.isTrue(customerName != null && !"".equals(customerName.trim()), "要查询的客户姓名不能为空");
		String hql = "select distinct t from TransformerInfo t, CustomerTransformerInfo ct, CustomerInfo ci where t.transformerName = ct.transformerName and ci.customerNumber = ct.customerNumber ";
		Map<String, Object> params = new HashMap<>();
		hql += "and ci.customerName = :customerName ";
		params.put("customerName", customerName);
		return baseDaoI.getList(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransformerInfo> findByExample(TransformerInfo transformerInfo) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		return hibernateUtil.exampleQuery(transformerInfo).list();
	}
	
	@Override
	public void changeFault(Integer id, boolean isFault) {
		try {
			TransformerInfo transformer = baseDaoI.getById(TransformerInfo.class, id);
			transformer.setIsFault(isFault);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addList(List<TransformerInfo> trans) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		hibernateUtil.addList(trans, 100);
	}

	@Override
	public void del(Serializable id) {
		try {
			baseDaoI.delete(baseDaoI.getById(TransformerInfo.class, (Integer)id));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateBdCoords(TransformerInfo transformerInfo) {
		List<TransformerInfo> coordsTransformers = new ArrayList<>();

		for (TransformerInfo transformer :
				findByExample(transformerInfo)) {
			if(transformer.getLongitude() != null && transformer.getLongitude().trim().length() > 0) {	//排除没有坐标的变压器
				coordsTransformers.add(transformer);
			}
		}

		ArrayList<TransformerInfo> tempList = new ArrayList<>();
		tempList.add(coordsTransformers.get(0));
		for (int i = 1; i < coordsTransformers.size(); i++) {
			if(i % 100 == 0) {
				injectBdCoord(tempList);
				tempList = new ArrayList<>();
			}
			tempList.add(coordsTransformers.get(i));
		}
		injectBdCoord(tempList);
	}

	public void injectBdCoord(ArrayList<TransformerInfo> tempList) {
		TransServiceModel serviceModel = coordsConvertionService.convert(tempList);
		for (int i = 0; i < tempList.size(); i++) {
            BdCoord bdCoord = serviceModel.getResult().get(i);
            tempList.get(i).setBdCoorsX(bdCoord.getX());
            tempList.get(i).setBdCoorsY(bdCoord.getY());
        }
	}


}

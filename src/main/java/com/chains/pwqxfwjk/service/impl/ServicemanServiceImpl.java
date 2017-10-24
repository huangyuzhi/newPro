package com.chains.pwqxfwjk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.PersonInfo;
import com.chains.pwqxfwjk.model.Serviceman;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.pwqxfwjk.util.HibernateUtil;

@Service("servicemanServiceImpl")
public class ServicemanServiceImpl implements ServicemanService {
	@Autowired
	private BaseDaoI<Serviceman> basedaoI;
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public void assignMission(Serviceman serviceman, String missionId) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Criteria criteria =  hibernateUtil.exampleQuery(serviceman);
		List<Serviceman> list = criteria.list();
		for(Serviceman persistentServiceman : list) {
			persistentServiceman.setMissionId(missionId);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Serviceman> findByExample(Serviceman serviceman) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Criteria criteria =  hibernateUtil.exampleQuery(serviceman);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void fillPersonInfo(List<PersonInfo> personInfos) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		String sql = "select s.*, m.* from serviceman s LEFT JOIN WQT_MISSION m ON s.mission_id = m.mission_id";
		List<Object[]> results =  hibernateUtil.executeSql(sql, new HashMap<String, Object>())
		.addEntity(Serviceman.class)
		.addEntity(WqtMission.class).list();
		for(PersonInfo personInfo : personInfos) {
			for(Object[] objs : results) {
				Serviceman serviceman = (Serviceman) objs[0];
				WqtMission mission = (WqtMission) objs[1];
				if(personInfo.getMemberId().equals(serviceman.getWqtMemberId())) {
					personInfo.setServiceman(serviceman);
					personInfo.setWqtMission(mission);
				}
				
			}
		}
	}
	
	@Override
	public List<Serviceman> findExecuteServiceman() {
		String hql = "from Serviceman s where s.missionId is not null";
		return basedaoI.getList(hql);
	}
	@Override
	public void assignMission(List<String> wqtIds, String missionId) {
		String hql = "update Serviceman s set s.missionId = :missionId where s.wqtMemberId in (:list)";
		Map<String, Object> params = new HashMap<>();
		params.put("missionId", missionId);
		params.put("list", wqtIds);
		basedaoI.del(hql, params);
	}
	
	
}

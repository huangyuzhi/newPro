package com.chains.pwqxfwjk.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.model.Serviceman;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.FaultStaticViewModel;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.service.TroubleLineService;
import com.chains.pwqxfwjk.service.WqtMissionService;
import com.chains.pwqxfwjk.util.FaultStatisticsDetailQueryParam;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.chains.util.QcRowBounds;
import com.chains.util.StringUtil;

@Service("faultStatisticsDetailServiceImpl")
public class FaultStatisticsDetailServiceImpl implements FaultStatisticsDetailService {

	@Autowired
	private BaseDaoI<FaultStatisticsDetail> baseDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private WqtMissionService wqtMissionServiceImpl; 
	
	@Autowired
	private ServicemanService servicemanServiceImpl;
	
	@Autowired
	private TransformerInfoService transformerInfoService;
	
	@Autowired
	private TroubleLineService troubleLineServiceImpl;
	
	private Map<String, String> propField = new HashMap<>();
	
	{
		propField.put("category", "category");
		propField.put("faultDevice", "fault_device");
		propField.put("faultDeviceVoltageLevel", "fault_device_voltage_level");
		propField.put("faultCategory", "fault_category");
	}
	@Override
	public List<FaultStatisticsDetail> find(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds) {
		return find(faultStatisticsDetail, rowBounds, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaultStatisticsDetail> find(FaultStatisticsDetail faultStatisticsDetail, QcRowBounds rowBounds, FaultStatisticsDetailQueryParam queryParam) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Criteria criteria = hibernateUtil.exampleQuery(faultStatisticsDetail, rowBounds);
		if(queryParam != null ) {
			if(queryParam.getBeginDate() != null && queryParam.getEndDate() != null) {
				criteria.add(Restrictions.between("sendMissionTime", queryParam.getBeginDate(), queryParam.getEndDate()));
			}
			
			if(queryParam.isSort()) {
				Assert.isTrue(queryParam.getOrder() != null && queryParam.getSortedBy() != null, "排序属性和排序规则不能为空");
				if(queryParam.getOrder().equals("asc")) {
					criteria.addOrder(Order.asc(queryParam.getSortedBy()));
				}else {
					criteria.addOrder(Order.desc(queryParam.getSortedBy()));
				}
			}
		}
		List<FaultStatisticsDetail> list = criteria.list();
		return list;
	}

	@Override
	public Long count(FaultStatisticsDetail faultStatisticsDetail, FaultStatisticsDetailQueryParam queryParam) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		Criteria criteria = hibernateUtil.exampleQuery(faultStatisticsDetail);
		if(queryParam != null ) {
			if(queryParam.getBeginDate() != null && queryParam.getEndDate() != null) {
				criteria.add(Restrictions.between("sendMissionTime", queryParam.getBeginDate(), queryParam.getEndDate()));
			}
		}
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public Long count(FaultStatisticsDetail faultStatisticsDetail ) {
		return count(faultStatisticsDetail, null);
	}
	
	@Override
	public void add(FaultStatisticsDetail faultStatisticsDetail) {
		baseDao.add(faultStatisticsDetail);
	}

	@Override
	public void del(Integer id) {
		try {
			FaultStatisticsDetail faultStatisticsDetail = baseDao.getById(FaultStatisticsDetail.class, id);
			List<WqtMission> missions = wqtMissionServiceImpl.findByExample(new WqtMission(faultStatisticsDetail.getMissionId()));
			List<Serviceman> servicemans = servicemanServiceImpl.findByExample(new Serviceman(faultStatisticsDetail.getMissionId()));
			if(missions.size() > 0) {
				WqtMission mission = missions.get(0);
				if(mission.getDestinationDeviceType().equals("transformer")) {
					transformerInfoService.changeFault(mission.getDestinationKey(), false);
				}else if(mission.getDestinationDeviceType().equals("line")) {
					List<Integer> ids = new ArrayList<>();
					ids.add(mission.getDestinationKey());
					troubleLineServiceImpl.delTroubleLine(ids);
				}
			}
			if(servicemans.size() > 0) {
				Serviceman serviceman = servicemans.get(0);
				serviceman.setMissionId(null);
			}
			baseDao.del(faultStatisticsDetail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String assignMissionAndStatistics(WqtMission wqtMission, FaultStatisticsDetail faultStatisticsDetail, TroubleLine troubleLine) throws Exception {
		//将任务派发到外勤通终端
		String missionId = wqtMissionServiceImpl.assignMission(wqtMission);
		List<String> ids = StringUtil.getIdsToList(wqtMission.getServiceman());
		servicemanServiceImpl.assignMission(ids, wqtMission.getMissionId());
		faultStatisticsDetail.setMissionId(missionId);
		
		if(wqtMission.getDestinationDeviceType().equals("transformer")) {
			transformerInfoService.changeFault(wqtMission.getDestinationKey(), true);	
		}else if(wqtMission.getDestinationDeviceType().equals("line")) {
			wqtMission.setDestinationKey(troubleLineServiceImpl.addTroubleLine(troubleLine));
		}else {
			//do nothing
		}
		baseDao.add(faultStatisticsDetail);
		return missionId;
	}

	@Override
	public void backfill(FaultStatisticsDetail faultStatisticsDetail) {
		try {
			FaultStatisticsDetail entity = baseDao.getById(FaultStatisticsDetail.class, faultStatisticsDetail.getId());
			entity.setCategory(faultStatisticsDetail.getCategory());
			entity.setReviewTime(faultStatisticsDetail.getReviewTime());
			entity.setReviewPerson(faultStatisticsDetail.getReviewPerson());
			entity.setSatisfaction(faultStatisticsDetail.getSatisfaction());
			entity.setDescriptor(faultStatisticsDetail.getDescriptor());
			
			//能够修改抢修人员回填的字段
			entity.setFaultDevice(faultStatisticsDetail.getFaultDevice());
			entity.setRepairContent(faultStatisticsDetail.getRepairContent());
			entity.setFaultCategory(faultStatisticsDetail.getFaultCategory());
			entity.setPerformerArriveTime(faultStatisticsDetail.getPerformerArriveTime());
			entity.setCustomerDevice(faultStatisticsDetail.getCustomerDevice());
			entity.whetherArriveTimeout();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void backfillMission(WqtBackfill wqtBackfill, String missionId) {
		FaultStatisticsDetail queryParam = new FaultStatisticsDetail();
		queryParam.setMissionId(missionId);
		List<FaultStatisticsDetail> faults = this.find(queryParam, new QcRowBounds());
		Assert.isTrue(faults.size() > 0);
		FaultStatisticsDetail faultStatisticsDetail = faults.get(0);
		BeanUtils.copyProperties(wqtBackfill, faultStatisticsDetail);
		faultStatisticsDetail.setRepairEndtime(wqtBackfill.getFinishTime());
		faultStatisticsDetail.setRepairTimeout(false);
		
		faultStatisticsDetail.whetherArriveTimeout();
	}
	
	@Override
	public byte[] staticExcel(Integer year, Integer month, List<String> groupFields) {
		List<FaultStaticViewModel> statics = viewModel(year, month, groupFields);
		try {
			return new FaultStaticExcel(groupFields).majorFlow(statics);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<FaultStaticViewModel> viewModel(Integer year, Integer month, List<String> groupFields) {
		Session session = sessionFactory.openSession();
		String selectClause = "select ",
			   orderbyClause = " ORDER BY ",
			   finalSql = "";
		for (String groupField : groupFields) {
			selectClause += " f." + propField.get(groupField) + " " + groupField + ", ";
			orderbyClause += " f." + propField.get(groupField) + ", ";
		}
		selectClause += " f.amount currentMonthAmount, " +
				" f2.amount totalAmount, f3.amount lastMonthAmount, f4.amount lastYearAmount ";
		orderbyClause =  orderbyClause.substring(0, orderbyClause.length() - 2);
		finalSql = selectClause + " from " + templateSql(groupFields, year, month, "f") +
				" INNER  JOIN " + templateSql2(groupFields, year, month, "f2") +
				" ON " + joinOnCondition("f", "f2", groupFields) + 
				" LEFT  JOIN " + templateSql(groupFields, year, month - 1, "f3") +
				" ON " + joinOnCondition("f", "f3", groupFields) + 
				" LEFT  JOIN " + templateSql(groupFields, year - 1, month, "f4") +
				" ON " + joinOnCondition("f", "f4", groupFields) +
				orderbyClause;
		SQLQuery query = session.createSQLQuery(finalSql);
		List<FaultStaticViewModel> list = query.setResultTransformer(Transformers.aliasToBean(FaultStaticViewModel.class)).list();
		return list;
	}
	
	private String templateSql(List<String> groupFields, Integer year, Integer month, String alias) {
		String selectClause = "(SELECT ",
			   fromClause = " FROM fault_statistics_detail " + alias,
			   whereClause = " WHERE "+alias+".year = "  + year + " and "+alias+".month = " + month,
			   groupClause = " GROUP BY ";
		for (String groupField : groupFields) {
			selectClause += propField.get(groupField) + ", ";
			groupClause += propField.get(groupField) + ", ";
		}
		selectClause += " count(*) amount ";
		groupClause = groupClause.substring(0, groupClause.length() - 2);
		return selectClause + fromClause + whereClause + groupClause + ") " + alias;
	}
	
	private String templateSql2(List<String> groupFields, Integer year, Integer month, String alias) {
		String selectClause = "(select ",
			   fromClause = " FROM fault_statistics_detail " + alias,
			   whereClause = " WHERE "+alias+".year = "  + year + " and "+alias+".month <= " + month,
			   groupClause = " GROUP BY ";
		for (String groupField : groupFields) {
			selectClause += propField.get(groupField) + ", ";
			groupClause += propField.get(groupField) + ", ";
		}
		selectClause += " count(*) amount ";
		groupClause = groupClause.substring(0, groupClause.length() - 2);
		return selectClause + fromClause + whereClause + groupClause + ") " + alias;
	}
	
	private String joinOnCondition(String alias, String alias2, List<String> groupFields) {
		String result = " 1 = 1 ";
		for (String groupField : groupFields) {
			result += " and " + alias + "." + propField.get(groupField) + " = " + alias2 + "." + propField.get(groupField);
		}
		return result;
	}
}

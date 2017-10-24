package com.chains.pwqxfwjk.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chains.dao.BaseDaoI;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.other.MissionAdapter;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.WqtMissionService;
import com.chains.pwqxfwjk.util.HibernateUtil;
import com.troyjj.crypt.Encrypt;

@Service("wqtMissionServiceimpl")
public class WqtMissionServiceImpl implements WqtMissionService {

	private String missionUrl;
	private String secureKey;
	@Autowired
	private BaseDaoI<WqtMission> baseDaoI;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private FaultStatisticsDetailService faultStatisticsDetailServiceImpl;
	
	{
		InputStream in = PersonPositionServiceImpl.class.getResourceAsStream("/other.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		secureKey = prop.getProperty("secureKey");

		missionUrl = prop.getProperty("missionUrl");
	}
	
	@Override
	public String assignMission(WqtMission mission) throws Exception {
		mission.setStatus(2);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(missionUrl);
		MissionAdapter missionAdapter = new MissionAdapter(mission);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("excUser", missionAdapter.getExeUser()));
		nvps.add(new BasicNameValuePair("setTime",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(missionAdapter.getSetTime())));
		nvps.add(new BasicNameValuePair("title", missionAdapter.getTitle()));
		nvps.add(new BasicNameValuePair("content", missionAdapter.getContent()));
		nvps.add(new BasicNameValuePair("isHave", missionAdapter.getIsHave()));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		StringBuilder resultBuffer = new StringBuilder();
		HttpEntity entity2 = response2.getEntity();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent()));
			String tempLine = null;
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			EntityUtils.consume(entity2);
			String decryptMessage = Encrypt.decryptSSOPlain(resultBuffer.toString(), secureKey);
			JSONObject object = JSON.parseObject(decryptMessage);
			Boolean success = object.getBoolean("success");
			Assert.isTrue(success, "未成功发送任务");
			mission.setMissionId(object.getString("id"));
			baseDaoI.add(mission);
			return object.getString("id");
		} finally {
			response2.close();
		}
	}
	
	@Override
	public List<WqtBackfill> findBackfill(String ids) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://117.78.37.146:8090/outside/dispatcher/issTask/getTaskByIdsSec?adminAccount=13885078500&appName=WAIQINTONG");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("ids", ids));
		CloseableHttpResponse response2 = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			response2 = httpclient.execute(httpPost);
			StringBuilder resultBuffer = new StringBuilder();
		    HttpEntity entity2 = response2.getEntity();
			try {
			    BufferedReader reader = new BufferedReader(
						new InputStreamReader(entity2.getContent()));
			    String tempLine = null;
			    while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}
			    EntityUtils.consume(entity2);
			    String decryptMessage = Encrypt.decryptSSOPlain(resultBuffer.toString(), secureKey);
			    List<WqtBackfill> list = JSON.parseArray(decryptMessage, WqtBackfill.class);
			    return list;
			} finally {
				response2.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WqtMission getByMissonId(String missonId) {
		WqtMission wqtMission = new WqtMission();
		wqtMission.setMissionId(missonId);
		return findByExample(wqtMission).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WqtMission> findByExample(WqtMission wqtMission) {
		HibernateUtil hibernateUtil = new HibernateUtil(sessionFactory);
		return hibernateUtil.exampleQuery(wqtMission).list();
	}

	/* (non-Javadoc)
	 * @see com.chains.pwqxfwjk.service.PersonPositionService#backfillMission(com.chains.pwqxfwjk.representation.WqtBackfill, java.lang.String)
	 */
	@Override
	public void backfillMission(WqtBackfill wqtBackfill, String missionId) {
		WqtMission queryparam = new WqtMission();
		queryparam.setMissionId(missionId);
		List<WqtMission> list = findByExample(queryparam);
		Assert.isTrue(list.size() > 0);
		WqtMission mission = list.get(0);
		wqtBackfill.parse();
		mission.setStatus(wqtBackfill.getStatus());
		mission.setReplyContent(wqtBackfill.getReplyContent());
		mission.setPicPath(wqtBackfill.getPicPath());
		mission.setPublishTime(wqtBackfill.getPubTaskTime());
		mission.setFaultDevice(wqtBackfill.getFaultDevice());
		mission.setRepairContent(wqtBackfill.getRepairContent());
		mission.setFaultCategory(wqtBackfill.getFaultCategory());
		mission.setPerformerArriveTime(wqtBackfill.getPerformerArriveTime());
		mission.setRepairEndtime(wqtBackfill.getFinishTime());
		mission.setCustomerDevice(wqtBackfill.getCustomerDevice());
		
		faultStatisticsDetailServiceImpl.backfillMission(wqtBackfill, missionId);
	}
}

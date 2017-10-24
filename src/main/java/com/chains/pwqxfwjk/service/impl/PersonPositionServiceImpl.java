package com.chains.pwqxfwjk.service.impl;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.chains.pwqxfwjk.model.Serviceman;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.chains.pwqxfwjk.model.PersonInfo;
import com.chains.pwqxfwjk.model.PersonInfoCollection;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.service.PersonPositionService;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.pwqxfwjk.util.PersonPositionQueryParam;
import com.troyjj.crypt.Encrypt;

@Service("personPositionServiceImpl")
public class PersonPositionServiceImpl implements PersonPositionService {
	private static final Random RANDOM = new Random(47);
	private String urlStr;
	private String secureKey;

	private volatile int count;
	@Autowired
	private ServicemanService servicemanServiceImpl;
	{
		InputStream in = PersonPositionServiceImpl.class.getResourceAsStream("/other.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		SimpleDateFormat sdfStart = new SimpleDateFormat("YYYY-MM-dd 00:00:00");
		SimpleDateFormat sdfEnd = new SimpleDateFormat("YYYY-MM-dd 23:59:59");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, -2);
		Date roundDate = calendar.getTime();
		
		String temUrl = prop.getProperty("personPosition");
		urlStr = temUrl.replaceAll("\\{todayStart\\}", sdfStart.format(date));
		urlStr = urlStr.replaceAll("\\{todayEnd\\}", sdfEnd.format(date));
		urlStr = urlStr.replaceAll("\\{roundDateStart\\}", sdfStart.format(roundDate));
		secureKey = prop.getProperty("secureKey");

	}

	public PersonInfoCollection getPersonPosition(PersonPositionQueryParam param) {
		String urlStr = this.urlStr;
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(PersonPositionQueryParam.class);
		for (PropertyDescriptor descriptor : descriptors) {
			try {
				if (!descriptor.getName().equals("class")) {
					Object paramValue = descriptor.getReadMethod().invoke(param, (Object[]) null);
					urlStr += paramValue != null ? "&" + descriptor.getName() + "=" + paramValue : "";
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		String responseText = getRemoteData(urlStr);
		String decryptMessage = Encrypt.decryptSSOPlain(responseText, secureKey);
		PersonInfoCollection pic = JSON.parseObject(decryptMessage, PersonInfoCollection.class);
		//同步人员信息,目前只是
		servicemanServiceImpl.fillPersonInfo(pic.getResult());

		try {
			if(count % 5 == 0) {
				List<Serviceman> servicemen = servicemanServiceImpl.findByExample(new Serviceman());
				HashMap<String, Serviceman> map = new HashMap<>();
				for (Serviceman serviceman:
						servicemen) {
					map.put(serviceman.getWqtMemberId(), serviceman);
				}

				//修改serviceman的终端名
				for (PersonInfo personInfo :
						pic.getResult()) {
					String personInfoMemberId = personInfo.getMemberId();
					Serviceman serviceman = map.get(personInfoMemberId);
					if(personInfoMemberId != null && serviceman != null && personInfo.getName() != null &&
							!personInfo.getName().equals(serviceman.getTerminalName())) {
						serviceman.setTerminalName(personInfo.getName());
					}
				}
				count ++;	//不严格的统计
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pic;
	}

	/**
	 * 方法名称:getRemoteData<br>
	 * 方法描述: 获取远程的人员信息数据 <br>
	 * 
	 * @return 返回类型: String @exception
	 */
	private String getRemoteData(String urlStr) {
		URL localURL;
		StringBuffer resultBuffer;
		if(urlStr.contains("?")) {
			urlStr += "&_r_=" + RANDOM.nextInt();
		}else{
			urlStr += "?_r_=" + RANDOM.nextInt();
		}
		try {
			localURL = new URL(urlStr.replaceAll(" ", "%20"));
			URLConnection connection = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			/*
			 * httpURLConnection.setRequestProperty("Content-Type",
			 * "application/x-www-form-urlencoded");
			 */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			resultBuffer = new StringBuffer();
			String tempLine = null;
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new RuntimeException(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}
			try {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}
			} finally {
				if (reader != null) {
					reader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultBuffer.toString();
	}

	/**
	 * 方法名称:coordsConversion<br>
	 * 方法描述: 百度坐标转换服务 <br>
	 * 
	 * @return 返回类型: TransServiceModel @exception
	 */
	private void coordsConversion(List<PersonInfo> list) {
		TransServiceModel model = null;
		if (list.size() > 100) { // 百度坐标的转换服务一次最多转换100个坐标,当超过100时需要发送多个请求。
			int beginIndex = 0, ceil = list.size() / 100 + 1;
			for (int i = 1; i <= ceil; i++) {
				List<PersonInfo> subPersons = null;
				if (i == ceil) { // 最后一次转换所有坐标
					subPersons = list.subList(beginIndex, list.size());
				} else {
					subPersons = list.subList(beginIndex, 100 * i);
				}
				// System.out.println("第" + i + "次转换,数量为" + subPersons.size() +
				// "个, beginIndex的值为" + beginIndex);
				TransServiceModel transData = JSON.parseObject(getRemoteData(generateConvertCoordsRequest(subPersons)),
						TransServiceModel.class);
				if (model == null) {
					model = transData;
				} else { // model已被初始化
					model.getResult().addAll(transData.getResult());
				}
				beginIndex = 100 * i;
			}
		} else {
			model = JSON.parseObject(getRemoteData(generateConvertCoordsRequest(list)), TransServiceModel.class);
		}
		for (int i = 0; i < model.getResult().size(); i++) {
			list.get(i).setUserLat(model.getResult().get(i).getX().toString());
			list.get(i).setUserLng(model.getResult().get(i).getY().toString());
		}
	}

	/**
	 * 方法名称:generateConvertCoordsRequest<br>
	 * 方法描述: 生成坐标转换请求的url字符串,注意，最多只能转换100个坐标，超过将抛出异常 <br>
	 * 
	 * @return 返回类型: String @exception
	 */
	private String generateConvertCoordsRequest(List<PersonInfo> list) {
		Assert.isTrue(list.size() <= 100, "一次请求最多能转换100个坐标");
		Assert.isTrue(list.size() > 0, "要转换的坐标不能为空");
		String bdConvertUrl = "http://api.map.baidu.com/geoconv/v1/?", coordsPrefix = "coords=", requestUrl = "";
		StringBuilder coordsData = new StringBuilder(coordsPrefix);
		for (int i = 0; i < list.size(); i++) {
			coordsData.append(list.get(i).getUserLng() + ",");
			if (i < list.size() - 1) {
				coordsData.append(list.get(i).getUserLat() + ";");
			} else { // 最后一个的时候
				coordsData.append(list.get(i).getUserLat()); // 有病，经纬度搞错了
				requestUrl = bdConvertUrl + coordsData
						+ "&from=1&to=5&ak=dnTeK9nGVeFhbvvWZ2eTKHjI&qq-pf-to=pcqq.temporaryc2c";
			}
		}
		return requestUrl;
	}
}

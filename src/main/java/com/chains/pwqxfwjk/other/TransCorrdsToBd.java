package com.chains.pwqxfwjk.other;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.chains.pwqxfwjk.model.LineInfoFor10kV;

public class TransCorrdsToBd {
	public static void main(String[] args) throws  Exception {
		//将数据库中所有10Kv的数据查询出来
		List<LineInfoFor10kV> list =  get10KvData();
		//每100gps个坐标转换为Bd坐标并写到数据库
		String bdConvertUrl = "http://api.map.baidu.com/geoconv/v1/?";
		String coordsPrefix = "coords=";
		StringBuilder coordsData = new StringBuilder(coordsPrefix);
		for(int i = 0; i < list.size(); i++) {
			coordsData.append(list.get(i).getLongitude() + ",");
			if(i % 99 == 0 && i != 0) {	//每一百个一组进行转换
				coordsData.append(list.get(i).getLatitude());
				String requestUrl = bdConvertUrl + coordsData + "&from=1&to=5&ak=dnTeK9nGVeFhbvvWZ2eTKHjI&qq-pf-to=pcqq.temporaryc2c";
				TransServiceModel model = bdConveter(requestUrl);
			}else {
				coordsData.append(list.get(i).getLatitude() + ";") ;
			}
		}
		
	}

	public static List<LineInfoFor10kV> get10KvData() throws Exception {
		URL localURL = new URL("http://127.0.0.1:8080/newPro/lineInfo/with10kvForJson.do?sortedBy=id");
		URLConnection connection = localURL.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		if (httpURLConnection.getResponseCode() >= 300) {
			throw new RuntimeException(
					"HTTP Request is not success, Response code is "
							+ httpURLConnection.getResponseCode());
		}
		try {
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			List<LineInfoFor10kV> list = JSON.parseArray(resultBuffer.toString(), LineInfoFor10kV.class);
			return list;
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
	}
	
	public static TransServiceModel bdConveter(String url) throws Exception {
		URL localURL = new URL(url);
		URLConnection connection = localURL.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		if (httpURLConnection.getResponseCode() >= 300) {
			throw new Exception(
					"HTTP Request is not success, Response code is "
							+ httpURLConnection.getResponseCode());
		}

		try {
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			TransServiceModel model = JSON.parseObject(resultBuffer.toString(), TransServiceModel.class);
			return model;
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
	}
}

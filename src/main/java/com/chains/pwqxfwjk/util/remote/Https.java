package com.chains.pwqxfwjk.util.remote;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Https {
    /**
     * http的post方法
     * @return 请求返回的内容
     */
    public static String post(String url, Map<String, String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (String key :
                params.keySet()) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            StringBuilder resultBuffer = new StringBuilder();

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String tempLine;
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            EntityUtils.consume(entity);
            return resultBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

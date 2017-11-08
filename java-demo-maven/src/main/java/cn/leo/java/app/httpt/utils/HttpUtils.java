package cn.leo.java.app.httpt.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static final String charset = "UTF-8";
	
	public static void main(String[] args) {
		String url = "http://10.200.3.232:14466/payment/pay/zbankInstalApp.do";
		HttpResult rs = postRequest(url, null);
		System.out.println(rs.isOk);
		System.out.println(rs.statusCode);
		System.out.println(rs.msg);
		
	}
	public static HttpResult getRequest(String url) {
		HttpResult result = new HttpResult();
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if(200 == statusLine.getStatusCode()){
				result.isOk = true;
			}
			result.statusCode = String.valueOf(statusLine.getStatusCode());
			HttpEntity entity = response.getEntity();
			String msg = EntityUtils.toString(entity, charset);
			result.msg = msg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static HttpResult postRequest(String url, Map<String, String> params) {
		HttpResult result = new HttpResult();
		UrlEncodedFormEntity form = buildForm(params);
		
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			httpPost.setEntity(form);
			response = httpClient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			if(200 == statusLine.getStatusCode()){
				result.isOk = true;
			}
			result.statusCode = String.valueOf(statusLine.getStatusCode());
			HttpEntity entity = response.getEntity();
			String msg = EntityUtils.toString(entity, charset);
			result.msg = msg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static UrlEncodedFormEntity buildForm(Map<String, String> params){
		if(params == null){
			return null;
		}
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			formparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		return form;
	}
}



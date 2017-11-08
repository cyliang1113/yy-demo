package cn.leo.java.app.httpt.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class RsaSign {
	public static String buildPaySignature(Map<String, String> signParams, String privateKeyFilePath) throws Exception {

		List<String> keys = new ArrayList<String>(signParams.keySet());
		Collections.sort(keys);
		String content = "";
		Charset charset = Charset.forName("UTF-8");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = signParams.get(key);
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			
			if (!StringUtils.equals("beforeURL", key) && !StringUtils.equals("serverNotifyURL", key)) {
				value = URLEncoder.encode(value, charset.name());
			}

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				content = content + key + "=" + value;
			} else {
				content = content + key + "=" + value + "&";
			}
		}

		if (content.endsWith("&")) {
			content.substring(0, content.length() - 1);
		}
		System.out.println("签名原文:" + content);
		return RSA.rsaSignKeyInFile(content, privateKeyFilePath, RSA.charset);
	}

	public static String buildFinSignature(Map<String, String> signParams, String privateKeyFilePath) throws Exception {
		List<String> list = new ArrayList<String>();
		for (String v : signParams.values()) {
			if (v != null) {
				list.add(v);
			}
		}
		Collections.sort(list);
		String text = "";
		for (String v : list) {
			text += URLEncoder.encode(v, "UTF-8");
		}
		return RSA.rsaSignKeyInFile(text, privateKeyFilePath, RSA.charset);
	}
}

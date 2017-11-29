package cn.leo.java.app.httpt;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.leo.java.app.httpt.utils.HttpResult;
import cn.leo.java.app.httpt.utils.HttpUtils;
import cn.leo.java.app.httpt.utils.RsaSign;

/**
 * http请求助手
 * 
 * @author chenyouliang
 *
 */
public class HttpT {
	public static final String privateKeyFilePath = "D:/git-local-repo/java-demo/java-demo-maven/src/main/java/cn/leo/java/app/httpt/utils/HeartyPri.key";

	public static void main(String[] args) {
		fin();
		//pay();
	}

	public static void pay() {
		String url = "http://10.200.3.232:14466/payment/pay/zbankInstalApp.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", "10058");
		params.put("instalment", "3");
		params.put("productId", "2987842");
		params.put("category", "CATEGORY_SINGLE_TICKET");
		params.put("buCode", "LOCAL_BU");
		params.put("objectId", "62972529");
		params.put("objectType", "ORD_ORDER");
		params.put("amount", "66600");
		params.put("paymentType", "PAY");
		params.put("bizType", "VST_ORDER");
		params.put("royaltyParameters", "");
		params.put("clientIp", "10.115.1.243");
		params.put("clientDfp", "JFFHjotybtdGcP8siZuUjHp-8w48LpRW");

		String[] signParamsNameArr = new String[] { "userId", "instalment", "productId", "category", "buCode",
				"objectId", "objectType", "amount", "paymentType", "bizType", "royaltyParameters", "clientIp",
				"clientDfp" };

		buildParams(params, signParamsNameArr, "pay", null);

		HttpResult rs = HttpUtils.postRequest(url, params);
		System.out.println(rs.isOk);
		System.out.println(rs.statusCode);
		System.out.println(rs.msg);
	}

	public static void fin() {
		// ?userId=10058&signature=213123&bizType=A09

		//String url = "http://jr.lvmama.com/instalment/h5";
		String url = "http://jr.lvmama.com/bt/h5";
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", "10058");
		params.put("bizType", "A08");
		params.put("eqmtIdNo", "009809S91202");
		params.put("eqmtVersion", "Android");
		params.put("eqmtType", "Huawei");
		params.put("eqmtIP", "10.1.23.123");
		params.put("eqmtGPS", "180.2345,32.2321");

		String[] signParamsNameArr = new String[] { "userId", "bizType" };

		buildParams(params, signParamsNameArr, "fin", null);

		HttpResult rs = HttpUtils.postRequest(url, params);
		System.out.println(rs.isOk);
		System.out.println(rs.msg);
	}

	public static void buildParams(Map<String, String> params, String[] signParamsNameArr, String signMethod, String signKeyName) {
		Map<String, String> signParams = new HashMap<String, String>();
		for (String name : signParamsNameArr) {
			signParams.put(name, params.get(name));
		}

		try {
			String signature = null;
			if ("pay".equals(signMethod)) {
				signature = RsaSign.buildPaySignature(signParams, privateKeyFilePath);
			}
			if ("fin".equals(signMethod)) {
				signature = RsaSign.buildFinSignature(signParams, privateKeyFilePath);
			}
			if(StringUtils.isNotBlank(signKeyName)){
				if(StringUtils.isNotBlank(signature))
					params.put(signKeyName, signature);
			}else{
				if(StringUtils.isNotBlank(signature))
					params.put("signature", signature);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

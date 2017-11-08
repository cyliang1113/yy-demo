package cn.leo.java.app.httpt;

import java.util.HashMap;
import java.util.Map;

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

		buildParams(params, signParamsNameArr, 1);

		HttpResult rs = HttpUtils.postRequest(url, params);
		System.out.println(rs.isOk);
		System.out.println(rs.msg);
	}

	public static void fin() {
		// ?userId=10058&signature=213123&bizType=A09

		String url = "http://jr.lvmama.com/instalment/h5";
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", "10058");
		params.put("bizType", "A09");

		String[] signParamsNameArr = new String[] { "userId", "bizType" };

		buildParams(params, signParamsNameArr, 2);

		HttpResult rs = HttpUtils.postRequest(url, params);
		System.out.println(rs.isOk);
		System.out.println(rs.msg);
	}

	public static void buildParams(Map<String, String> params, String[] signParamsNameArr, int signMethod) {
		Map<String, String> signParams = new HashMap<String, String>();
		for (String name : signParamsNameArr) {
			signParams.put(name, params.get(name));
		}

		try {
			if (1 == signMethod) {
				String signature = RsaSign.buildPaySignature(signParams, privateKeyFilePath);
				params.put("signature", signature);
			}
			if (2 == signMethod) {
				String signature = RsaSign.buildFinSignature(signParams, privateKeyFilePath);
				params.put("signature", signature);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

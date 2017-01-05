package cn.leo.weibofetch;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class WeiboFetch {
	private static final Logger LOG = Logger.getLogger(WeiboFetch.class);

	public static void main(String[] args) {
		HttpClient client = null;
		HttpMethod method = null;
		PostMethod postMethod = null;
		try {
			client = new HttpClient();
			method = new GetMethod(
					"https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00cpKvSEz3kCPE08a818e634gc9s4D&count=10");

			client.executeMethod(method);

			// LOG.info(method.getStatusCode());
			// LOG.info(method.getResponseBodyAsString());

			String returnMsg = method.getResponseBodyAsString();

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> rootMsg = mapper.readValue(returnMsg, Map.class);
			ArrayList<Object> tweetList = (ArrayList<Object>) rootMsg.get("statuses");

			// LOG.info(tweetList);

			if (tweetList != null) {
				String tweetId = null;
				String preStr = "{\"index\":{\"_id\":\"{0}\"}}";
				StringBuilder sb = new StringBuilder();
				for (Object tweet : tweetList) {
					tweetId = String.valueOf(((Map<String, Object>) tweet).get("id"));
					String Str = mapper.writeValueAsString(tweet);

					sb.append(preStr.replace("{0}", tweetId)).append("\n");
					sb.append(Str).append("\n");
				}
				LOG.info(sb.toString());

				postMethod = new PostMethod("http://hadoop21:9200/weibo/tweet/_bulk");
				postMethod.setRequestEntity(new StringRequestEntity(sb.toString()));
				client.executeMethod(postMethod);
				LOG.info(postMethod.getResponseBodyAsString());
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}

	}
}

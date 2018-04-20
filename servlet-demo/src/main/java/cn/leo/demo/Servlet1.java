package cn.leo.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Servlet1 extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4790868723100199925L;
	
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("get请求");
		
		// 请求参数key的集合
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()){
			logger.info(names.nextElement());
		}
		logger.info("==================================");
		
		// 请求参数key=value的map key=[value1, value2, ...], 相同key的value放在同一个数组中, key没有相同的数组里value也只有一个
		Map parameterMap = req.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		for (String key : keySet) {
			String[] value = (String[]) parameterMap.get(key);
			logger.info(key + "=" + Arrays.toString(value));
		}
		
		
		
		
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.print("ok");
		outputStream.close();
	}
}

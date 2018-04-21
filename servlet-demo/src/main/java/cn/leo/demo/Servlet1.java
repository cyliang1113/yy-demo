package cn.leo.demo;

import java.io.BufferedReader;
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
		logger.info("==================================");
		
		// 返回key对应的value, 如果相同key有多个则返回第一个value
		String p1 = req.getParameter("p1");
		logger.info("p1=" + p1);
		logger.info("==================================");
		
		// 返回key对应value的数组, 如果相同key只有一个数组中这有一个元素
		String[] p1Arr = req.getParameterValues("p1");
		logger.info("p1=" + Arrays.toString(p1Arr));
		
		
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.print("ok");
		outputStream.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("post请求");
		
		// getReader必须在getParameter前面, 不然从流里面就读不到了
		// 获得请求体里的内容
		BufferedReader inR = req.getReader();
//		String readLine = inR.readLine();
//		logger.info(readLine);
		char[] cb = new char[1024];
		int read = inR.read(cb);
		logger.info(read);
		logger.info(Arrays.toString(cb));
		logger.info("==================================");
		
		// 请求参数key的集合
		Enumeration<String> names = req.getParameterNames();
		logger.info("getParameterNames");
		while(names.hasMoreElements()){
			logger.info(names.nextElement());
		}
		logger.info("==================================");
		
		// 请求参数key=value的map key=[value1, value2, ...], 相同key的value放在同一个数组中, key没有相同的数组里value也只有一个
		Map parameterMap = req.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		logger.info("getParameterMap");
		for (String key : keySet) {
			String[] value = (String[]) parameterMap.get(key);
			logger.info(key + "=" + Arrays.toString(value));
		}
		logger.info("==================================");
		
		// 返回key对应的value, 如果相同key有多个则返回第一个value
		String p1 = req.getParameter("p1");
		logger.info("getParameter");
		logger.info("p1=" + p1);
		logger.info("==================================");
		
		// 返回key对应value的数组, 如果相同key只有一个数组中这有一个元素
		String[] p1Arr = req.getParameterValues("p1");
		logger.info("getParameterValues");
		logger.info("p1=" + Arrays.toString(p1Arr));
		logger.info("==================================");

		
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.print("ok");
		outputStream.close();
	}
	
	
}

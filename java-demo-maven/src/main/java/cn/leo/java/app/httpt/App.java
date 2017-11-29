package cn.leo.java.app.httpt;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import cn.leo.java.app.httpt.utils.HttpResult;
import cn.leo.java.app.httpt.utils.HttpUtils;

public class App {
	public static JEditorPane editorPane = null;
	public static JTextArea consoleArea = null;
	public static JButton btn = null;
	
	public static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	
	public static void main(String[] args) {
		// 创建frame
		JFrame mainFrame = createMainFrame();
		//
		fillMainFrame(mainFrame);
		// 显示窗体
		mainFrame.setVisible(true);
	}

	/**
	 * 创建主窗体
	 */
	public static JFrame createMainFrame() {
		// 创建frame
		JFrame mainFrame = new JFrame("HTTP工具");
		// 调整frame的大小和初始位置
		mainFrame.setSize(650, 800);
		mainFrame.setLocation(400, 200);
		mainFrame.setResizable(false);
		
		mainFrame.setFont(new Font("宋体", Font.PLAIN, 30));
		
		
		// 增加窗口监听事件
		mainFrame.addWindowListener(new WindowAdapter() {
			// 重写窗口关闭事件
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		return mainFrame;
	}

	/**
	 * 主窗体
	 */
	public static void fillMainFrame(JFrame mainFrame){
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
		
		mainFrame.setLayout(flowLayout);
		
		//
		URL url = App.class.getClassLoader().getResource("cn/leo/java/app/httpt/layout/layout01.html");
		editorPane = new JEditorPane();
	//	editorPane.setPreferredSize(new Dimension(600, 350));
		editorPane.setEditable(false); // 请把editorPane设置为只读，不然显示就不整齐
		try {
			editorPane.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// console
		consoleArea = new JTextArea();
		consoleArea.setLineWrap(true);
		consoleArea.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(consoleArea);
		scroll.setPreferredSize(new Dimension(600, 300));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		// button
		JButton btn = new JButton("发送请求");
		btn.setPreferredSize(new Dimension(600, 30));
		btn.setVisible(true);
		btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
//            	String str = App.this.consoleArea.getText();
                
            	String text = editorPane.getText();
            	Document document = Jsoup.parse(text);
            	Element element_http_url = document.getElementById("http_url");
            	String http_url = element_http_url.text();
            	
            	Element element_http_params = document.getElementById("http_params");
            	String http_params = element_http_params.text();
            	
            	Element element_sign_params = document.getElementById("sign_params");
            	String sign_params = element_sign_params.text();
            	
            	Element element_sign_method = document.getElementById("sign_method");
            	String sign_method = element_sign_method.text();
            	
//            	Element element_sign_key_name = document.getElementById("sign_key_name");
//            	String sign_key_name = element_sign_key_name.text();
            	
            	StringBuffer sb = new StringBuffer();
            	sb.append("请求url : ").append(http_url).append("\n");
            	sb.append("请求参数 : ").append(http_params).append("\n");
            	sb.append("加入签名的请求参数 : ").append(sign_params).append("\n");
            	sb.append("签名方法 : ").append(sign_method).append("\n");
//            	sb.append("请求参数中签名key名称 : ").append(sign_key_name).append("\n");
            	
            	// http
            	String url = http_url;
        		Map<String, String> params = new HashMap<String, String>();
        		if(StringUtils.isNotBlank(http_params)){
        			String[] split = http_params.split("&");
        			for (String key_val : split) {
        				String[] arr = key_val.split("=");
        				params.put(arr[0], arr[1]);
					}
        		}

        		String[] signParamsNameArr = new String[]{};
        		if(StringUtils.isNotBlank(sign_params)){
        			String[] split = sign_params.split("&");
        			signParamsNameArr = split;
        		}

        		HttpT.buildParams(params, signParamsNameArr, sign_method, null);

        		HttpResult rs = HttpUtils.postRequest(url, params);
            	
        		sb.append(rs.isOk).append("\n");;
        		sb.append(rs.statusCode).append("\n");;
        		sb.append(rs.msg).append("\n");;
            	
            	consoleArea.setText(sb.toString());
            	
            }
        });
		
		mainFrame.add(editorPane);
		mainFrame.add(btn);
		mainFrame.add(scroll);
	}
	
	
}

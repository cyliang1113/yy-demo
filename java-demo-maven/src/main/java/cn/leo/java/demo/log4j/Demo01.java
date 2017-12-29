package cn.leo.java.demo.log4j;

import org.apache.log4j.Logger;

public class Demo01 {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Demo01.class);
		logger.info("hello");
	}
}

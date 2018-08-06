package cn.leo.mybatisdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.leo.mybatisdemo.po.UserUser;
import cn.leo.mybatisdemo.service.UserUserService;

public class Test {
	private static final Log logger = LogFactory.getLog(Test.class);
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserUserService service = (UserUserService) ac.getBean("userUserService");

		UserUser userUser = service.findByUserId(1L);

		logger.info(userUser);
		
//		UserUser userUser2 = new UserUser();
//		userUser2.setUsername("yoo");
//		userUser2.setCellphone("15166668899");
//		service.saveUser(userUser2);
//
//		Object userUser2 = new Object();
//		logger.info(userUser2);
//		logger.info(userUser2.hashCode());

	}
}

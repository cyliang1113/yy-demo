package cn.leo.ibatisdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.leo.ibatisdemo.po.UserUser;
import cn.leo.ibatisdemo.service.UserUserService;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserUserService service = (UserUserService) ac.getBean("userUserService");

		UserUser userUser = service.selectByUserId(10000L);

		System.out.println(userUser);

	}
}

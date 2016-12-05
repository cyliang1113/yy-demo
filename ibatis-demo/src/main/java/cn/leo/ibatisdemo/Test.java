package cn.leo.ibatisdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.leo.ibatisdemo.po.UserUser;
import cn.leo.ibatisdemo.service.UserUserService;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserUserService service = (UserUserService) ac.getBean("userUserService");

		UserUser userUser = service.selectByUserId(10000L);

		System.out.println(userUser);

		Map map = new HashMap<String, Object>();
		map.put("username", "leon");
		List<UserUser> selectByParam = service.selectByParam(map);
		for (UserUser userUser2 : selectByParam) {
			System.out.println(userUser2);
		}
	}
}

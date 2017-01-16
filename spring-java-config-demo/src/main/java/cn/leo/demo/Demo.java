package cn.leo.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.leo.demo.config.ApplicationConfig;
import cn.leo.demo.service.OrderServiceImpl;
import cn.leo.demo.service.UserServiceImpl;
import cn.leo.demo.utils.DataSource;

public class Demo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		UserServiceImpl bean = (UserServiceImpl) context.getBean("userService");
		System.out.println(bean.findUsername());

		OrderServiceImpl orderService = (OrderServiceImpl) context.getBean("orderService");
		System.out.println(orderService.findOrderPrice());

		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource.getUsername() + "/" + dataSource.getPassword());
	}
}

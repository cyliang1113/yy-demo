package cn.leo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.leo.demo.dao.UserDao;
import cn.leo.demo.service.UserService;
import cn.leo.demo.service.UserServiceImpl;

@Configuration
public class ApplicationConfigService {

	// 这个方法的参数 会自动注入
	@Bean
	public UserService userService(UserDao userDao) {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao);
		return userService;
	}
}

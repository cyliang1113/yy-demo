package cn.leo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.leo.demo.service.UserServiceImpl;


/**
 * java config
 * 
 * ApplicationConfig类 相当于 一个xml文件
 *
 */
@Configuration
public class ApplicationConfig {
	
	/**
	 *	相当于  <bean id="userService" class="cn.leo.demo.service.UserServiceImpl"/>
	 */
	@Bean
	public UserServiceImpl userService() {
		return new UserServiceImpl();
	}
}

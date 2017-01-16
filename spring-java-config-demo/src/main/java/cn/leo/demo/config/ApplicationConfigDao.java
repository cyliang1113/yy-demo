package cn.leo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.leo.demo.dao.UserDao;
import cn.leo.demo.dao.UserDaoImpl;

@Configuration
public class ApplicationConfigDao {

	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}
}

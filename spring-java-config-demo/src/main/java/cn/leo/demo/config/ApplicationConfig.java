package cn.leo.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * java config
 * 
 * ApplicationConfig类 相当于 一个xml文件
 * 
 */
@Configuration
// 引入其他JavaConfig
@Import({ ApplicationConfigDao.class, ApplicationConfigService.class })
// 引入xml配置文件
@ImportResource("classpath:applicationContext-service.xml")
public class ApplicationConfig {

	/**
	 * 相当于 <bean id="userService" class="cn.leo.demo.service.UserServiceImpl"/>
	 */

}

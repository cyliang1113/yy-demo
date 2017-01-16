package cn.leo.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * java config
 * 
 * ApplicationConfig类 相当于 一个xml文件
 * 
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
// 引入其他JavaConfig
@Import({ ApplicationConfigDao.class, ApplicationConfigService.class, ApplicationConfigDataSource.class })
// 引入xml配置文件
@ImportResource("classpath:applicationContext-service.xml")
public class ApplicationConfig {

	/**
	 * 相当于 <bean id="userService" class="cn.leo.demo.service.UserServiceImpl"/>
	 */

}

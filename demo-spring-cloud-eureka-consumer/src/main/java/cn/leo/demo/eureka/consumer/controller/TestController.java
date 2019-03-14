package cn.leo.demo.eureka.consumer.controller;

import cn.leo.demo.eureka.consumer.feign.TestFacadeFeign;
import cn.leo.demo.eureka.consumer.feign.UserServiceFacadeFeign;
import cn.leo.demo.eureka.provider.po.UserUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
	static private Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	TestFacadeFeign testFacadeFeign;

	@GetMapping("/date")
	public String sayHi() {
		Date date = new Date();
		logger.info(date.toString() + ", " + date.getTime());
		Map<String, Object> date1 = testFacadeFeign.date(date);
		logger.info(date1.toString());
		return "s";
	}


}

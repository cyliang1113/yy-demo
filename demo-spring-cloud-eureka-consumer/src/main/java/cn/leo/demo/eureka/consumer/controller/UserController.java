package cn.leo.demo.eureka.consumer.controller;

import cn.leo.demo.eureka.consumer.feign.UserServiceFacadeFeign;
import cn.leo.demo.eureka.provider.po.UserUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceFacadeFeign userServiceFacadeFeign;

	@GetMapping("/sayHi")
	public String sayHi(@RequestParam("name") String name) {
		Map<String, Object> stringObjectMap = userServiceFacadeFeign.sayHi(name);
		return stringObjectMap.toString();
	}

	@GetMapping("/login")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password") String password
	) {
		UserUser userUser = new UserUser();
		userUser.setUsername(username);
		userUser.setPassword(password);
		return userServiceFacadeFeign.login(userUser);
	}


}

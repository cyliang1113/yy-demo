package cn.leo.demo.eureka.provider.msi.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.leo.demo.eureka.provider.service.UserServiceImpl;

@RestController
public class UserController {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/service/user/username")
	public Map<?, ?> getUsername() {
		logger.info(System.currentTimeMillis());
		String username = userService.getUsername();
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		return map;
	}
}

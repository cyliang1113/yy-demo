package cn.leo.demo.eureka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/user/username")
	public String getUsername() {
		String rs = restTemplate.getForEntity("http://eureka-provider/service/user/username", String.class).getBody();
		return rs;
	}
}

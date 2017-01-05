package cn.leo.springbootdemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/user/login")
	public String login() {
		return "hello user";
	}
}

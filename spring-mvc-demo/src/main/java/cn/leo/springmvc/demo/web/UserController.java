package cn.leo.springmvc.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@RequestMapping("/user/login")
	@ResponseBody
	public String login() {
		return "hello";
	}
}

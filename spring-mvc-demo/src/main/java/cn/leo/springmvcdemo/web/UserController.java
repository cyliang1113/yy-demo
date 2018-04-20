package cn.leo.springmvcdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/loginpage")
	public String loginPage() {
		userServiceImpl.getUsername();
		return "loginpage";
	}
	
	@RequestMapping("/loginout")
	@ResponseBody
	public String loginout() {
		return "loginout";
	}
}

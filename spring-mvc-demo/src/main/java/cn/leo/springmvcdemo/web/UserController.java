package cn.leo.springmvcdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/loginpage")
	public String loginPage() {
		return "loginpage";
	}
	
	@RequestMapping("/loginout")
	@ResponseBody
	public String loginout() {
		return "loginout";
	}
}

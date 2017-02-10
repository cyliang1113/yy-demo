package cn.leo.sysback.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home" })
	public String homepage() {
		return "home";
	}
}

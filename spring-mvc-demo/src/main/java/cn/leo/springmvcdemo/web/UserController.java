package cn.leo.springmvcdemo.web;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/loginpage")
	public String loginPage() {
		userServiceImpl.getUsername();
		return "loginpage";
	}
	
	@RequestMapping("/loginout")
	@ResponseBody
	public String loginout(
		@RequestParam(value = "age", required = false) Integer age,
		@RequestParam(value = "name", required = false) String name,
		@RequestParam(value = "price", required = false) BigDecimal price
	) {
		logger.info("age:" + age);
		logger.info("name:" + name);
		logger.info("price:" + price);
		return "loginout";
	}
}

package cn.leo.demo.eureka.provider.facade.impl;

import cn.leo.demo.eureka.provider.facade.UserServiceFacade;
import cn.leo.demo.eureka.provider.po.UserUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserServiceFacadeImpl implements UserServiceFacade {
    static private Logger logger = LoggerFactory.getLogger(UserServiceFacadeImpl.class);

	@Override
	public Map<String, Object> sayHi(@RequestParam("name") String name) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("return msg", "Hi, " + name + "!");
		return res;
	}

    @Override
    public String login(@RequestBody UserUser userUser) {
        logger.info("username: " + userUser.getUsername());
        logger.info("password: " + userUser.getPassword());
	    if(userUser!= null){
            return userUser.getUsername() + "/" + userUser.getPassword() + ", 登录成功!";
        }else {
            return userUser.getUsername() + "/" + userUser.getPassword() + ", login失败!!!";
        }
    }
}

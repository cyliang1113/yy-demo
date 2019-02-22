package cn.leo.demo.eureka.provider.facade;

import cn.leo.demo.eureka.provider.po.UserUser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/userServiceFacade")
public interface UserServiceFacade {

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    Map<String, Object> sayHi(@RequestParam("name") String name);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(@RequestBody UserUser userUser);
}

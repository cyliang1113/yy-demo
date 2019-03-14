package cn.leo.demo.eureka.provider.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@RequestMapping("/testFacade")
public interface TestFacade {
    @RequestMapping(value = "/date", method = RequestMethod.GET)
    Map<String, Object> date(@RequestParam("date") Date date);
}

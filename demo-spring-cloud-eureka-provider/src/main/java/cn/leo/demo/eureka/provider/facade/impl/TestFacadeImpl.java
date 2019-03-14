package cn.leo.demo.eureka.provider.facade.impl;

import cn.leo.demo.eureka.provider.facade.TestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestFacadeImpl implements TestFacade {
    static private Logger logger = LoggerFactory.getLogger(TestFacadeImpl.class);

    @Override
    public Map<String, Object> date(@RequestParam("date") Date date) {
        logger.info(date.toString());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("date", new Date());
        return map;
    }
}

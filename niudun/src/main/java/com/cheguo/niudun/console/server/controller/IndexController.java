package com.cheguo.niudun.console.server.controller;

import com.cheguo.foundation.core.common.Result;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/3/16
 * @since 1.0
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/test")
    public Result test() {
        Result<Map<String, String>> ret = new Result<>();

        Map<String, String> map = Maps.newHashMap();

        map.put("name", "spy");
        ret.setData(map);
        ret.setSuccess(true);

        return ret;
    }
}

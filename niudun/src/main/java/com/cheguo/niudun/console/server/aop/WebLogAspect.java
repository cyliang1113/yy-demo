package com.cheguo.niudun.console.server.aop;

import com.alibaba.fastjson.JSON;
import com.cheguo.apollo.sys.base.common.SysLogDto;
import com.cheguo.foundation.core.util.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印请求参数
 *
 * @author shi.pengyan
 * @version 1.0 2018-04-25
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.cheguo.niudun.console.server.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //TODO refine it
        String requestStr = RequestLogUtil.makeLog(joinPoint);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            //log.info("cost={}ms", stopWatch.getTotalTimeMillis());
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            SysLogDto logDto = SysLogDto.builder()
                                        .url(request.getRequestURI())
                                        .requestMethod(request.getMethod())
                                        .request(requestStr)
                                        .response(JSON.toJSONString(ret))
                                        .cost(stopWatch.getTotalTimeMillis())
                                        .build();

            //log.info("{}", logDto);
        }


        return ret;
    }
}

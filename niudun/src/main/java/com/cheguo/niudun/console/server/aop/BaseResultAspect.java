package com.cheguo.niudun.console.server.aop;

import com.cheguo.foundation.core.common.Result;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018-04-25
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class BaseResultAspect {

    @Autowired
    private MessageSource messageSource;


    @Pointcut("execution(public * com.cheguo.apollo.console.server.biz.*.service.impl..*.*(..))")
    public void checkBaseResult() {

    }

    @AfterReturning(pointcut = "checkBaseResult()", returning = "rtv")
    public void doAfterReturning(JoinPoint jp, Object rtv) throws Throwable {

        if (rtv instanceof Result) {
            Result result = (Result) rtv;
            if (!result.getSuccess()) {
                if (Strings.isNullOrEmpty(result.getErrorMessage())) {
                    result.setErrorMessage(
                            messageSource.getMessage(
                                    result.getErrorCode(),
                                    result.getErrorParam(),
                                    result.getErrorCode(),
                                    Locale.CHINESE)
                    );
                }
            }
        }
    }
}

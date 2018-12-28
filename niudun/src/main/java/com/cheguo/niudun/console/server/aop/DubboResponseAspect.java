package com.cheguo.niudun.console.server.aop;

import com.alibaba.fastjson.JSONObject;
import com.cheguo.foundation.core.common.CoreErrorConst;
import com.cheguo.foundation.core.common.PageResult;
import com.cheguo.foundation.core.common.Result;
import com.cheguo.foundation.core.exception.BaseAppException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.Serializable;

/**
 * dubbo异常捕获
 *
 * @author spy
 * @version 1.0 2018-04-25
 * @since 1.0
 */
// 默认不启用
//@Component
//@Aspect
@Slf4j
public class DubboResponseAspect {


    @Around(value = "execution(* com.cheguo.apollo.console.server.biz.*.facade.impl..*.*(..))&& @annotation(annotation)",
            argNames = "joinPoint")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();

        Object[] args = joinPoint.getArgs();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String parametersJson = JSONObject.toJSONString(args);

        log.info("dubbo method {} is being invoking with parameters: {} .", methodSignature.getName(), parametersJson);

        try {
            Object proceed = joinPoint.proceed();

            return proceed;
        } catch (Throwable throwable) {
            log.error("dubbo facade impl exception occurred!", throwable);

            Result<Serializable> result;


            Class returnType = methodSignature.getReturnType();

            if (returnType != null) {
                if (PageResult.class.getName().equalsIgnoreCase(returnType.getName())) {
                    result = new PageResult();
                } else if (Result.class.getName().equalsIgnoreCase(returnType.getName())) {
                    result = new Result<>();
                } else {
                    result = new Result<>();
                }
            } else {
                result = new Result<>();
            }


            result.setSuccess(false);

            if (throwable instanceof BaseAppException) {

                BaseAppException appEx = (BaseAppException) throwable;

                result.setErrorCode(appEx.getCode());
                result.setErrorMessage(appEx.getDesc());
            } else {
                result.setErrorCode(CoreErrorConst.SYS_DUBBO_EXCEPTION_ERROR);
                result.setErrorMessage(throwable.getMessage());
            }

            return result;
        } finally {
            //TODO do something
        }

    }

}

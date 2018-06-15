package cn.leo.springdemo.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


public class LogAdvice {
    private Log log = LogFactory.getLog(LogAdvice.class);

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            log.info(method.getName()+"-----前");
            result = joinPoint.proceed();
            log.info(method.getName()+"-----后");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}

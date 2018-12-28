package com.cheguo.niudun.console.server.base.common;

import java.lang.annotation.*;

/**
 * 系统日志，只要有这个注解，就可以输出入参和出参
 *
 * @author spy
 * @version 1.0 2018/04/25
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
}

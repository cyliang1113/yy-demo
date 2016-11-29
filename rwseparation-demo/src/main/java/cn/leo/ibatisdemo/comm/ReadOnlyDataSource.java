package cn.leo.ibatisdemo.comm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 只读数据源，使用在service方法上，service方法里只能有读数据库操作
 * 
 * @author chenyouliang
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnlyDataSource {

}

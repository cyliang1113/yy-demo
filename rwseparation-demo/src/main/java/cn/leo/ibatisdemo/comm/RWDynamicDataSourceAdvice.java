package cn.leo.ibatisdemo.comm;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class RWDynamicDataSourceAdvice {
	protected Logger log = Logger.getLogger(this.getClass());

	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			if (method.getAnnotation(ReadOnlyDataSource.class) == null) {
				// 没有ReadOnlyDataSource注解，有写数据库操作
				RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.DEFAULT_DATA_SOURCE);
			} else {
				// 有ReadOnlyDataSource注解，没有写数据库操作，使用只读数据源
				RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.READ_ONLY_DATA_SOURCE);
			}
			log.debug("RWDynamicDataSourceAdvice DataSourceName: " + RWDynamicDataSourceNameHolder.getDataSouceName());
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			RWDynamicDataSourceNameHolder.clear();
		}
		return result;
	}

}

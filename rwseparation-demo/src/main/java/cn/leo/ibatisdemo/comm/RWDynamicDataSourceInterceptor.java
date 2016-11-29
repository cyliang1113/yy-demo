package cn.leo.ibatisdemo.comm;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * 读写分离拦截器
 * 
 * @author chenyouliang
 * 
 */
public class RWDynamicDataSourceInterceptor implements MethodInterceptor {
	protected Logger log = Logger.getLogger(this.getClass());

	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Object result = null;
		try {
			Method method = invocation.getMethod();
			if (method.getAnnotation(ReadOnlyDataSource.class) == null) {
				// 没有ReadOnlyDataSource注解，有写数据库操作
				RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.DEFAULT_DATA_SOURCE);
			} else {
				// 有ReadOnlyDataSource注解，没有写数据库操作
				RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.READ_ONLY_DATA_SOURCE);
			}
			log.debug("RWDynamicDataSourceInterceptor DataSourceName: " + RWDynamicDataSourceNameHolder.getDataSouceName());
			result = invocation.proceed();
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			RWDynamicDataSourceNameHolder.clear();
		}
		return result;
	}

}

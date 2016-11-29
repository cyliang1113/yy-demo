package cn.leo.ibatisdemo.comm;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 读写分离拦截器
 * 
 * @author chenyouliang
 * 
 */
public class RWDynamicDataSourceInterceptor implements MethodInterceptor {

	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Object result = null;
		try {
			String dataSouceName = RWDynamicDataSourceNameHolder.getDataSouceName();

			if (dataSouceName == null) {
				Method method = invocation.getMethod();
				if (method.getAnnotation(ReadOnlyDataSource.class) == null) {
					// 没有ReadOnlyDataSource注解，有写数据库操作
					RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.DEFAULT_DATA_SOURCE);
				} else {
					// 有ReadOnlyDataSource注解，没有写数据库操作
					RWDynamicDataSourceNameHolder.setDataSourceName(RWDynamicDataSource.READ_ONLY_DATA_SOURCE);
				}
			}
			result = invocation.proceed();
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			RWDynamicDataSourceNameHolder.clear();
		}
		return result;
	}

}

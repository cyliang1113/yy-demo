package cn.leo.java.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
	private Subject object;

	public MyHandler(Subject object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("MyHandler.invoke()");
		return method.invoke(object, args);
	}

}

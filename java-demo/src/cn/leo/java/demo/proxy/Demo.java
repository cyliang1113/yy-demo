package cn.leo.java.demo.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {
	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();

		Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[] { Subject.class }, new MyHandler(
				realSubject));

		System.out.println(subject.getClass());

		Method[] declaredMethods = subject.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}
		
		Method[] methods = subject.getClass().getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}

		// subject.doSomething();
		// subject.saySomething();
	}
}

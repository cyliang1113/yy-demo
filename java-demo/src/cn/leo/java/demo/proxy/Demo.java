package cn.leo.java.demo.proxy;

import java.lang.reflect.Proxy;

public class Demo {
	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();

		Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[] { Subject.class }, new MyHandler(
				realSubject));

		subject.doSomething();
		subject.saySomething();
	}
}

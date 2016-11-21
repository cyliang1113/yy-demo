package cn.leo.java.demo.proxy;

public class RealSubject implements Subject {

	@Override
	public void doSomething() {
		System.out.println("RealSubject.doSomething()");

	}

	@Override
	public void saySomething() {
		System.out.println("RealSubject.saySomething()");

	}

}

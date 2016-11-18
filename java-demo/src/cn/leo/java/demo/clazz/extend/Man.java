package cn.leo.java.demo.clazz.extend;

public class Man extends Person {
	
	public int age = 1;
	
	public Man() {
		System.out.println("构造方法Man()");
		System.out.println("构造方法Man(), class:" + this.getClass());
	}
	
	public Man(String name){
		System.out.println("构造方法Man(String name)");
	}
	
	
	public void getName() {
		System.out.println("Man.getName()");
	}
	
	
}

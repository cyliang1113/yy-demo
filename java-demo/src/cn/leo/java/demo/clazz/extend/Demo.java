package cn.leo.java.demo.clazz.extend;

public class Demo {
	public static void main(String[] args) {
		Man m = new Man();
		System.out.println(m.age);
		m.getClassName();
		

		Person p = m;
		System.out.println(p.age);
		p.getClassName();
		
		Gay gay = new Gay();
		gay.isPerson();
		

		// System.out.println("------------------");
		// new Man("gg");
	}
}

package cn.leo.java.demo;

import java.util.HashMap;

public class HelloWorld {
	public static void main(String[] args) {
		HashMap<Object,Object> hashMap = new HashMap<>();
		hashMap.put("dsfd", "1.111");
		String aa = "1.111";
		Double a = (Double) (hashMap.get("dsfd"));
	}
}

package cn.leo.java.demo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms10m -Xmx10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -XX:+UseConcMarkSweepGC
 * -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -Xms20m -Xmx20m -Xmn5m -XX:+PrintGCDetails
 * 
 * @author leo
 */
public class Demo01 {

	public static void main(String[] args) {
		int m1 = 1 * 1024 * 1024;
		int m5 = 5 * 1024 * 1024;
		
		
		List<byte[]> list = new ArrayList<byte[]>();
		int i = 1;
		for (;;) {
			System.out.println(i++);
			list.add(new byte[m5]);
		}
	}
}

package cn.leo.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo01 {
	public static Lock lock = new ReentrantLock(true);
	
	public static void main(String[] args) {
		new Thread("111") {
			@Override
			public void run() {
				print1();
			}
		}.start();

		new Thread("222") {
			@Override
			public void run() {
				print2();
			}
		}.start();
	}

	public static void print1() {
		char[] hello = { 'h', 'e', 'l', 'l', 'o' };
		for (int s = 0; s < 10000; s++){
			lock.lock();
			for (int i = 0; i < hello.length; i++) {
				System.out.print(hello[i]);
			}
			System.out.println();
			lock.unlock(); // unlock()最好放在finally{}中
		}
		
	}

	public static void print2() {
		char[] world = { 'w', 'o', 'r', 'l', 'd' };
		for (int s = 0; s < 10000; s++){
			lock.lock();
			for (int i = 0; i < world.length; i++) {
				System.out.print(world[i]);
			}
			System.out.println();
			lock.unlock();
		}
		
	}

}

package cn.leo.demo.thread.lock.locksupport;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Demo01 {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(
				() -> {
					System.out.println("1111");
					LockSupport.park();
					System.out.println("2222");
				}
		);
		thread.start();
		System.out.println("----");
		thread.sleep(50 * 1000);
		LockSupport.unpark(thread);
	}



}

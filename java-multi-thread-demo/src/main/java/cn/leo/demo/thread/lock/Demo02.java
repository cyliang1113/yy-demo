package cn.leo.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock(true);
		lock.lock();
		System.out.println("111");
		lock.unlock();
	}
}

package cn.leo.java.demo.thread.jdkexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);

		MyRunnable runnable = new MyRunnable();
		for (int i = 0; i < 6; i++) {
			es.submit(runnable);
		}
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("I am thread: " + Thread.currentThread().getName() + " ,time: " + System.currentTimeMillis());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

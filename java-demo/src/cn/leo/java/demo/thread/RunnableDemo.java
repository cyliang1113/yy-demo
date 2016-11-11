package cn.leo.java.demo.thread;

public class RunnableDemo {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("I am thread: " + Thread.currentThread().getName());

		FirstRunnable runnable = new FirstRunnable();

		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);

		thread1.start();
		thread2.start();
		thread3.start();

		System.out.println("main end.");
	}

}

class FirstRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("I am thread: " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

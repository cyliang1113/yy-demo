package cn.leo.demo.thread.sync;

/*
 * 三个线程交替执行
 */
public class Demo02 {
	public static void main(String[] args) {

		Demo02PrintMsgg printMsg = new Demo02PrintMsgg();

		new Demo02MyThread1(printMsg).start();
		new Demo02MyThread2(printMsg).start();

		try {
			printMsg.print1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class Demo02MyThread1 extends Thread {
	private Demo02PrintMsgg pm;

	public Demo02MyThread1(Demo02PrintMsgg pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		try {
			pm.print2();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Demo02MyThread2 extends Thread {
	private Demo02PrintMsgg pm;

	public Demo02MyThread2(Demo02PrintMsgg pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		try {
			pm.print3();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Demo02PrintMsgg{
	private boolean print1 = true;
	private boolean print2 = false;
	private boolean print3 = false;
	private Object lock = new Object();

	public void print1() throws InterruptedException {
		for (int s = 1; s <= 5; s++) {
			synchronized (lock) {
				while (!print1) {
					lock.wait();
				}
				for (int i = 1; i <= 3; i++) {
					System.out.println("print1:" + i);
				}
				print1 = false;
				print2 = true;
				lock.notifyAll();
			} 
		}
	}

	public void print2() throws InterruptedException {
		for (int s = 1; s <= 5; s++) {
			synchronized (lock) {
				while (!print2) {
					lock.wait();
				}
				for (int i = 1; i <= 3; i++) {
					System.out.println("print2:" + i);
				}
				print2 = false;
				print3 = true;
				lock.notifyAll();
			}
		}
	}

	public void print3() throws InterruptedException {
		for (int s = 1; s <= 5; s++) {
			synchronized (lock) {
				while (!print3) {
					lock.wait();
				}
				for (int i = 1; i <= 3; i++) {
					System.out.println("print3:" + i);
				}
				print3 = false;
				print1 = true;
				lock.notifyAll();
			}
		}
	}
}

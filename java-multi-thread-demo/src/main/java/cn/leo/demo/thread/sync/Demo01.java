package cn.leo.demo.thread.sync;

/*
 * 主线程从1打印到3
 * 子线程从a打印到c
 * 主线程子线程交替执行5次
 */
public class Demo01 {
	public static void main(String[] args) {

		Demo01PrintMsg printMsg = new Demo01PrintMsg();

		new Demo01MyThread(printMsg).start();

		printMsg.printNum();

	}

}

class Demo01MyThread extends Thread {
	private Demo01PrintMsg pm;

	public Demo01MyThread(Demo01PrintMsg pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		pm.printChar();
	}
}

class Demo01PrintMsg {
	private boolean printNum = true;
	private Object lock = new Object();

	public void printNum() {
		for (int s = 1; s <= 5; s++) {
			synchronized (lock) {
				if (!printNum) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 1; i <= 3; i++) {
					System.out.println(i);
				}
				printNum = false;
				lock.notify();
			}
		}

	}

	public void printChar() {
		for (int s = 1; s <= 5; s++) {
			synchronized (lock) {
				if (printNum) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i <= 2; i++) {
					System.out.println((char) ('a' + i));
				}
				printNum = true;
				lock.notify();
			}
		}
	}
}

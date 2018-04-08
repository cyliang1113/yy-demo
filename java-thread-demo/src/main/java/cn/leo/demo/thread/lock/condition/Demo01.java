package cn.leo.demo.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 主线程从1打印到3
 * 子线程从a打印到c
 * 主线程子线程交替执行5次
 */
public class Demo01 {
	public static void main(String[] args) {

		PrintMsg printMsg = new PrintMsg();

		new MyThread1(printMsg).start();

		printMsg.printNum();

	}

}

class MyThread1 extends Thread {
	private PrintMsg pm;

	public MyThread1(PrintMsg pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		pm.printChar();
	}
}

class PrintMsg {
	private boolean printNum = true;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void printNum() {
		for (int s = 1; s <= 5; s++) {
			lock.lock();
			try {
				while(!printNum){
					condition.await();
				}
				for (int i = 1; i <= 3; i++) {
					System.out.println(i);
				}
				printNum = false;
				condition.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	public void printChar() {
		for (int s = 1; s <= 5; s++) {
			lock.lock();
			try {
				while(printNum){
					condition.await();
				}
				for (int i = 0; i <= 2; i++) {
					System.out.println((char) ('a' + i));
				}
				printNum = true;
				condition.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}

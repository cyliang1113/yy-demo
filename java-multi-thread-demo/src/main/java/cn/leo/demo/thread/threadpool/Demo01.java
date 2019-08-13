package cn.leo.demo.thread.threadpool;

import java.util.concurrent.*;

public class Demo01 {
	public static void main(String[] args) {
		int nThreads = 1;
		ExecutorService pool = new ThreadPoolExecutor(nThreads, nThreads,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());

		pool.execute(() -> {
			System.out.println("run1...");
			try {
				Thread.sleep( 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		pool.execute(() -> {
			System.out.println("run2...");
			try {
				Thread.sleep( 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		pool.execute(() -> {
			System.out.println("run3...");
			try {
				Thread.sleep( 1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		pool.execute(() -> {
			System.out.println("run4...");
			try {
				Thread.sleep( 1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		pool.shutdown();
	}
}

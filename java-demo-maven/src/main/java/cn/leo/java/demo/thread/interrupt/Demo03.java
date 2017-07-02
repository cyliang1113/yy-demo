package cn.leo.java.demo.thread.interrupt;

public class Demo03 {
	public static void main(String[] args) throws InterruptedException {

		Thread3 thread = new Thread3();

		thread.start();

		Thread.sleep(2000);

		thread.setStop();

		Thread.sleep(5000);

	}

	static class Thread3 extends Thread {
		private boolean stop = false;

		@Override
		public void run() {
			int i = 0;
			while (!stop) {
				i++;
				System.out.println("Thread3");
			}
			System.out.println(i);
		}

		public void setStop() {
			System.out.println("stop");
			this.stop = true;
		}
	}
}

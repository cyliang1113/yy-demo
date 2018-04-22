package cn.leo.demo.thread.sync;

public class Demo03 {
	public static int i;
	public static Object lock = new Object();
	
	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					synchronized (lock) {
						i++;
						synchronized (lock) {
							System.out.println(i);
						}
					}
				}
			}
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					synchronized (lock) {
						i++;
						synchronized (lock) {
							System.out.println(i);
						}
					}
				}
			}
		}.start();
	}
}

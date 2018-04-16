package cn.leo.demo.thread.sync.blockQueue;

public class MyBQueue {
	private Object[] queue;
	
	private int putIndex;
	private int takeIndex;
	
	private int count;
	
	private Object qLock = new Object();
	
	
	public MyBQueue(int capacity){
		if(capacity < 1){
			throw new IllegalArgumentException("queue capacity 要大于零.");
		}
		queue = new Object[capacity];
	}
	
	public void put(Object obj) throws InterruptedException{
		synchronized(qLock){
			while(count == queue.length){
				qLock.wait();
			}
			queue[putIndex] = obj;
			count++;
			System.out.println("队列里有"+count+"个");
			incr(putIndex);
			qLock.notifyAll();
		}
	}
	
	public Object take() throws InterruptedException{
		synchronized(qLock){
			while(count == 0){
				qLock.wait();
			}
			Object obj = queue[takeIndex];
			count--;
			System.out.println("队列里有"+count+"个");
			incr(takeIndex);
			qLock.notifyAll();
			return obj;
		}
	}
	
	
	
	private void incr(int index) {
		++index;
		if(index == queue.length){
			index = 0;
		}
	}

	public static void main(String[] args) {
		final MyBQueue myBQueue = new MyBQueue(5);
		new Thread("生产者1"){
			public void run() {
				int i = 1;
				while(true){
					try {
						String s = "A" + i;
						myBQueue.put(s);
						System.out.println("生产者1生产:" + s);
						i++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		new Thread("生产者2"){
			public void run() {
				int i = 1;
				while(true){
					try {
						String s = "B" + i;
						myBQueue.put(s);
						System.out.println("生产者2生产:" + s);
						i++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		new Thread("消费者1"){
			public void run() {
				while(true){
					try {
						String s = (String) myBQueue.take();
						System.out.println("消费者1消费:" + s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		new Thread(){
			public void run() {};
		}.start();
	}
	
}

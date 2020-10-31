package com.java.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker1 implements Runnable{
	private BlockingQueue<String> blockingQueue;

	public FirstWorker1(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		
		try {
			blockingQueue.put("B");
			blockingQueue.put("H");
			blockingQueue.put("F");
			Thread.sleep(1000);
			blockingQueue.put("A");
			Thread.sleep(1000);
			blockingQueue.put("E");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
}


class SecondWorker1 implements Runnable{
	private BlockingQueue<String> blockingQueue;

	public SecondWorker1(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
}

public class PriorityBlockingQueueExe {

	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		
		new Thread(new FirstWorker1(queue)).start();
		new Thread(new SecondWorker1(queue)).start();
		
		// priority queue stores the data in a order and returns the data in ordered form only

	}

}

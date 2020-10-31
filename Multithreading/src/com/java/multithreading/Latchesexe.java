package com.java.multithreading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Latchesexe {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(5);
		for(int i=0;i<5;++i)
			executorService.execute(new Worker2(i+1,latch));
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All the prerequisites are done....");
	}

}

class Worker2 implements Runnable
{
	
	private int id;
	private CountDownLatch countDownLatch;
	private Random random;
	
	public Worker2(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		
		doWork();
		countDownLatch.countDown();
		
	}

	private void doWork() {
		System.out.println("Thread with id "+this.id+" starts working...");
		
	}
}
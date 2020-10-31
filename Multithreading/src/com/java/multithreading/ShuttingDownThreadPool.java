package com.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work1 implements Runnable{
	private int id;
	public Work1(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Task with id "+id+" is in work - thread id : "+Thread.currentThread().getId());
		long duration = (long) (Math.random()*5);
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
}

public class ShuttingDownThreadPool {

	public static void main(String[] args) {
		//It is a single thread that will execute the tasks sequentially
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=0;i<5;++i) {
			executor.execute(new Work1(i+1));
		}
		//we prevent the executor to execute any further tasks
		executor.shutdown();
		//terminate actual (running) tasks
		try {
			
			if(!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				executor.shutdownNow();
			}
		}catch(InterruptedException e) {
			
			executor.shutdown();
		}
	}

}

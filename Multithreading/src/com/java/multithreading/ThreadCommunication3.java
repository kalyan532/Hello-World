package com.java.multithreading;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker{
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void producer() throws InterruptedException{
		lock.lock();
		System.out.println("In Producer method");
		condition.await();
		System.out.println("Again in Producer method...");
		
	}
	
	public void consumer() throws InterruptedException{
		lock.lock();
		Thread.sleep(2000);
		System.out.println("In Consumer Method");
		condition.signal();
		lock.unlock();
		
	}
	
}




public class ThreadCommunication3 {
	
	public static void main(String[] args) {
		
		Worker worker = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.producer();
				} catch (InterruptedException e) {	
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

}

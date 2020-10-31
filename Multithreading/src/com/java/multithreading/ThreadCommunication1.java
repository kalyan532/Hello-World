package com.java.multithreading;

import java.util.ArrayList;
import java.util.List;

class Processor{
	
	private List<Integer> list = new ArrayList<>();
	private static final int UPPER_LIMIT = 5;
	private static final int LOWER_LIMIT = 0;
	private final Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException{
		synchronized(lock) {
			
			while(true) {
				System.out.println("In thread "+Thread.currentThread().getId());
				if(list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for removing items...");
					lock.wait();
				}
				else {
					System.out.println("Adding : "+value);
					list.add(value);
					value++;
					//we can call the notify because the other thread will be notified
					//only when it is in waiting state
					lock.notify();
					
				}
				Thread.sleep(5000);
			}
			
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized(lock) {
			
			while(true) {
				
				System.out.println("In thread "+Thread.currentThread().getId());
				if(list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for removing items...");
					lock.wait();
				}
				else {
					System.out.println("Removing : "+list.remove(list.size()-1));
					lock.notify();
					
				}
				Thread.sleep(5000);
			}
			
		}
	}
}

public class ThreadCommunication1 {

	public static void main(String[] args) {
		
		Processor process = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.consumer();
				} catch (InterruptedException e) {
				}
			}
		});
		
		t1.start();
		t2.start();

	}

}

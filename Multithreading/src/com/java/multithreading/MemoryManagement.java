package com.java.multithreading;

public class MemoryManagement {
	
	public static int counter=0;
	
	//we have to make sure that this method is executed by only
	//one thread at a time
	
	public static synchronized void increment() {
		counter++;
		
		//System.out.println("Value "+counter+" Thread : "+Thread.currentThread().getId());
	}
	
	public static void process() {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					increment();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					increment();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("The Counter is : "+counter);
	}

	public static void main(String[] args) {
		
		process();
		
	}

}

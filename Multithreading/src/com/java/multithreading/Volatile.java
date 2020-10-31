package com.java.multithreading;


class Worker1 implements Runnable{
	
	private volatile boolean terminated;

	@Override
	public void run() {
		while(!terminated) {
			System.out.println("Worker class is Running..");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isTerminated() {
		return terminated;
	}
	
	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}
	
}

public class Volatile {

	public static void main(String[] args) {
		
		Worker1 worker = new Worker1();
		
		Thread t1 = new Thread(worker);
		t1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		worker.setTerminated(true);
		System.out.println("Algorithm is terminated...");
		
		

	}

}

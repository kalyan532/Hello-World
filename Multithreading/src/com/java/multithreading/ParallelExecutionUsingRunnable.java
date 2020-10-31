package com.java.multithreading;

public class ParallelExecutionUsingRunnable {
	
	static class Runner1 implements Runnable{
		//we made the class static because main() method is using it and main method handles better with static members

		@Override
		public void run() {
			for(int i=0;i<10;++i) {
				System.out.println("Runner1 : "+i);
			}
			
			System.out.println();
			
		}
	}

	static class Runner2 implements Runnable{
		

		@Override
		public void run() {
			for(int i=0;i<10;++i) {
				System.out.println("Runner2 : "+i);
			}
			System.out.println();
			
		}
	}

	public static void main(String[] args) {

		
		Thread t1  = new Thread(new Runner1());
		Thread t2  = new Thread(new Runner2());
		Thread t3  = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<10;++i) {
					System.out.println("Anonymous : "+i);
				}
				
			}
			
		}
		);
		
		
		t1.start();
		t2.start();
		t3.start();

	}

}

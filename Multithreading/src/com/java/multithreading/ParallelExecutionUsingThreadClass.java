package com.java.multithreading;


class Runner3 extends Thread{
	
	@Override
	public void run() {
		for(int i=0;i<10;++i) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner1 : "+i);
		}
		
	}
}

class Runner4 extends Thread{
	
	@Override
	public void run() {
		for(int i=0;i<10;++i) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner2 : "+i);
		}
	}
}

public class ParallelExecutionUsingThreadClass {
	

		public static void main(String[] args) {
			Thread t1 = new Runner3();
			Thread t2 = new Runner4();
			
			t1.start();
			t2.start();
			//adding join will make sure that print statement is executed after finishing execution of t1 and t2
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//below line is executed after starting t1 and t2 even though execution of t1 and t2 is not finished
			System.out.println("Finished executing Threads");

		}

}

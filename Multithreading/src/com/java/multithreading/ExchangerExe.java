package com.java.multithreading;

import java.util.concurrent.Exchanger;

class FirstThreadex implements Runnable{
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public FirstThreadex(Exchanger<Integer> exchanger) {
		
		this.exchanger = exchanger;
		
	}
	
	@Override
	public void run() {
		while(true) {
			counter = counter+1;
			System.out.println("FirstThread Incremented the counter: "+counter);
			
			try {
				counter  = exchanger.exchange(counter);
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
	}
}



class SecondThreadex implements Runnable{
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public SecondThreadex(Exchanger<Integer> exchanger) {
		
		this.exchanger = exchanger;
		
	}
	
	@Override
	public void run() {
		while(true) {
			counter = counter-1;
			System.out.println("SecondThread decremented the counter: "+counter);
			
			try {
				counter  = exchanger.exchange(counter);
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
	}
}

public class ExchangerExe {

	public static void main(String[] args) {
		
		Exchanger<Integer> exchanger = new Exchanger<>();
		
		new Thread(new FirstThreadex(exchanger)).start();
		new Thread(new SecondThreadex(exchanger)).start();

	}

}

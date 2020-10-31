package com.java.diningphilosophers;

import java.util.Random;

public class Philosopher implements Runnable {
	
	private int id;
	private volatile boolean full;
	private ChopStick leftChopStick;
	private ChopStick rightChopStick;
	private Random random;
	private int eatingCounter;
	
	
	
	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick) {
		super();
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		this.random = new Random();
	}

	@Override
	public void run() {
		try {
			
			//after eating n number of times we will terminate the thread
			while(!full) {
				think();
				if(leftChopStick.pickUp(this, States.LEFT)) {
					//the philosopher is able to acquire left chopStick
					if(rightChopStick.pickUp(this, States.RIGHT)) {
						//philosopher is able to up up right chopstick also
						eat();
						rightChopStick.putDown(this,States.RIGHT);
					}
					
					leftChopStick.putDown(this, States.LEFT);
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void think() throws InterruptedException{
		System.out.println(this+" is thinking..!");
		//the philosopher thinks for a random time between 1 to 1000 s
		Thread.sleep(random.nextInt(1000));
		
	}
	
	private void eat() throws InterruptedException{
		System.out.println(this+" is eating..!");
		eatingCounter++;
		//the philosopger thinks for a random time between 1 to 1000 s
		Thread.sleep(random.nextInt(1000));
		
	}
	
	public void setFull(boolean full) {
		this.full = full;
	}
	
	public boolean isFull() {
		return this.full;
	}
	
	@Override
	public String toString() {
		return "Philosopher "+id;
	}

	public int getEatingCounter() {
		return this.eatingCounter;
	}

}

package com.java.diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	
	private int id;
	private Lock lock;
	
	public ChopStick(int id) {
		this.id = id;
		this.lock = new  ReentrantLock();
	}
	
	public boolean pickUp(Philosopher philosopher, States state) throws InterruptedException {
		//this is where we will simulate deadlock
		if(lock.tryLock(10,TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher+" picked up "+this.toString());
			return true;
		}
		return false;
	}
	
	public void putDown(Philosopher philosopher, States state) {
		lock.unlock();
		System.out.println(philosopher+" put down "+state.toString()+" "+this);
	}
	
	@Override
	public String toString() {
		return "ChopStick "+id;
	}

}

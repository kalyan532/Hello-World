package com.java.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExe {

	public static void main(String[] args) {
		
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		
		try {
			queue.put(new DelayedWorker(1000,"This is the first message...."));
			queue.put(new DelayedWorker(10000,"This is the Second message...."));
			queue.put(new DelayedWorker(4000,"This is the Third message...."));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(!queue.isEmpty()) {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}


class DelayedWorker implements Delayed{
	
	private long duration;
	private String message;
	
	public DelayedWorker(long duration, String message) {
		this.duration = duration;
		this.message = message;
	}

	@Override
	public int compareTo(Delayed otherDelayed) {
		
		if(this.duration<((DelayedWorker) otherDelayed).getDuration()) {
			return -1;
		}
		else if(this.duration<((DelayedWorker) otherDelayed).getDuration()) {
			return +1;
		}
		return 0;
		
	}

	
	
	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.message;
	}
	
	
	
}

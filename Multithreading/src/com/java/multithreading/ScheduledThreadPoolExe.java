package com.java.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {
	@Override
	public void run() {
		System.out.println("Updating and downloading stock related data from web ...");
	}
}

public class ScheduledThreadPoolExe {

	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new StockMarketUpdater(),1000,5000,TimeUnit.MILLISECONDS);

	}

}

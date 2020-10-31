package com.java.diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophersExe {
	
	/*
	 * The main aim of this simulation is that it is possible to avoid thread starvation
	 *  -all the threads are going to be executed by the executor service
	 *  -  That we are able to avoid deadlocks because we used trylock()
	 * */

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = null;
		Philosopher[] philosophers =null;
		ChopStick[] chopSticks = null;
		
		try {
			
			philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
			chopSticks = new ChopStick[Constants.NUMBER_OF_CHOPSTICKS];
			
			for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;++i)
				chopSticks[i] = new ChopStick(i);
			
			executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
			
			for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;++i) {
				philosophers[i] = new Philosopher(i,chopSticks[i],chopSticks[(i+1)%Constants.NUMBER_OF_PHILOSOPHERS]);
				
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
			
			
			for(Philosopher philosopher:philosophers) {
				philosopher.setFull(true);
			}
		}finally {
			executorService.shutdown();
			
			while(!executorService.isTerminated())
				Thread.sleep(1000);
			
			for(Philosopher philosopher:philosophers) {
				System.out.println(philosopher+" eat #"+philosopher.getEatingCounter()+" times!");
			}
		

	}
	}

}

package com.java.forkjoinframework;

import java.util.concurrent.ForkJoinPool;

public class SimpleRecursiveTaskExe {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		SimpleRecursiveTask task = new SimpleRecursiveTask(120);
		System.out.println("Solution : "+pool.invoke(task));
	}

}

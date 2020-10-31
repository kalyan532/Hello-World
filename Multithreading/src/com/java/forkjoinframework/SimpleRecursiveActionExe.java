package com.java.forkjoinframework;

import java.util.concurrent.ForkJoinPool;

public class SimpleRecursiveActionExe {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		System.out.println("In Main---> Number of processors Available "+Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(102);
		pool.invoke(simpleRecursiveAction);
	}

}

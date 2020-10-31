package com.java.forkjoinframework;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

	private int simulatedWork;
	
	public SimpleRecursiveAction(int simulatedWork) {
		super();
		this.simulatedWork = simulatedWork;
	}



	@Override
	protected void compute() {

		if(simulatedWork>100) {
			System.out.println("Parallel Execution and split tasks..."+simulatedWork);
			
			SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork/2);
			SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork/2);
			
			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
			
		}
		else{
			System.out.println("No Need of parallel Execution, fork join framework is OK!! "+simulatedWork);
		}
	}

}

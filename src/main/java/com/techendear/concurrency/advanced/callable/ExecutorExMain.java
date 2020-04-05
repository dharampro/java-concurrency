package com.techendear.concurrency.advanced.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExMain {

	public static void main(String[] args) {

		Runnable task = ()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I am in: "+ Thread.currentThread().getName());
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		for(int i=0; i<10; i++) {
			executor.execute(task);
		}
		
		executor.shutdown();
	}

}

package com.techendear.concurrency.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicExecutorServiceExample {

	public static void main(String[] args) {

		Runnable[] threads = new Runnable[50];

		int n = 0;
		for (int i = 0; i < threads.length; i++) {
			threads[i] = () -> threadFunc(n);
		}

		ExecutorService se = Executors.newSingleThreadExecutor();

		try {
			for (int j = 0; j < threads.length; j++)
				se.execute(threads[j]);
		} finally {
			se.shutdown();
		}

	}

	public static void threadFunc(int n) {
		System.out.println("Thread: " + n++);
	}

}

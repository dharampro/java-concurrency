package com.techendear.concurrency.basics.runnable;

public class RunnablePattern {

	public static void main(String[] args) throws InterruptedException {
		LongWrapper longWrapper = new LongWrapper(0);
		Runnable runnable = () -> {
			synchronized (longWrapper) {
				for (int i = 0; i < 1_0000; i++) {
					longWrapper.incrementL();
				}
			}
		};

		Thread[] threads = new Thread[1_000];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		System.out.println("Value:" + longWrapper.getL());

	}
}

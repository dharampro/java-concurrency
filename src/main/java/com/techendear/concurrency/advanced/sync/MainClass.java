package com.techendear.concurrency.advanced.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {

		Runnable t = () -> getMe();

		ExecutorService execute = Executors.newFixedThreadPool(4);

		try {
			lock.lock();
			for (int i = 0; i < 50; i++) {
				execute.execute(t);
			}
		} finally {
			lock.unlock();
			System.out.println("Shutting down thread...");
			execute.shutdown();
		}
	}

	public static void getMe() {
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("In thread " + Thread.currentThread().getName() + " count: " + count);
			if (count == 10) {
				flag = false;
			}
			count++;
		}
	}
}

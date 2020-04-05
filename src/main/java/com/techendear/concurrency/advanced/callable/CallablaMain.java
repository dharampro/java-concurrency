package com.techendear.concurrency.advanced.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallablaMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		Callable<Integer> task = () -> {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName());
			return 6;
		};

		ExecutorService ex = Executors.newFixedThreadPool(4);
		try {
			for (int i = 0; i < 10; i++) {
				Future<Integer> res = ex.submit(task);
				System.out.println(res.get(1002, TimeUnit.MILLISECONDS));
			}
		} finally {
			System.out.println("Shutting Down.....");
			ex.awaitTermination(1000, TimeUnit.MILLISECONDS);
//			ex.shutdownNow();
			System.out.println("Executor Shutdown completed.....");
		}
	}
}

package com.techendear.concurrency.advanced.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

	public static void main(String[] args) throws InterruptedException {

		List<Integer> buffer = new ArrayList<Integer>(50);
		Lock lock = new ReentrantLock();
		Condition isEmpty = lock.newCondition();
		Condition isFull = lock.newCondition();

		class Consumer implements Callable<String> {

			@Override
			public String call() throws Exception {
				int count = 0;
				while (count++ < 50) {
					try {
						lock.lock();
						while (isEmpty(buffer)) {
							isEmpty.await();
						}

						buffer.remove(buffer.size() - 1);
						isFull.signalAll();
					} finally {
						lock.unlock();
					}
				}

				return "Consumed: " + (count - 1);
			}

			private boolean isEmpty(List<Integer> buffer) {
				return buffer.size() == 0;
			}

		}

		class Producer implements Callable<String> {

			@Override
			public String call() throws InterruptedException {
				int count = 0;

				while (count++ < 50) {
					try {
						lock.lock();
						while (isFull(buffer)) {
							isFull.await();
						}
						buffer.add(1);
						isEmpty.signalAll();
					} finally {
						lock.unlock();
					}

				}
				return "Produced: " + (count-1);
			}

			private boolean isFull(List<Integer> buffer) {
				return buffer.size() == 50;
			}

		}

		List<Producer> producers = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			producers.add(new Producer());
		}

		List<Consumer> consumer = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			consumer.add(new Consumer());
		}

		System.out.println("Producer and Consumer launched");

		List<Callable<String>> producerAndConsumer = new ArrayList<>();
		producerAndConsumer.addAll(producers);
		producerAndConsumer.addAll(consumer);

		ExecutorService executorService = Executors.newFixedThreadPool(4);

		try {
			List<Future<String>> futures = executorService.invokeAll(producerAndConsumer);

			futures.forEach(future -> {
				try {
					System.out.println(future.get());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			});

		} finally {
			executorService.shutdown();
			System.out.println("Executor Shutdown");
		}

	}
}

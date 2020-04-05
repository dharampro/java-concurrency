package com.techendear.concurrency.advanced.lockcond;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockCond {

	private Map<Long, String> cache = new HashMap<Long, String>();
	ReadWriteLock lock = new ReentrantReadWriteLock();

	Lock read = lock.readLock();
	Lock write = lock.writeLock();

	public static void main(String[] args) {

		LockCond cond = new LockCond();
		ExecutorService execute = Executors.newFixedThreadPool(4);
		try {
			for (int i = 0; i < 1_000; i++) {
				Runnable t1 = () -> cond.write(0L, "Writing:");
				Runnable t2 = () -> cond.read(0L);

				execute.execute(t1);
				execute.execute(t2);
			}
		} finally {
			execute.shutdown();
		}

	}

	public void write(Long key, String value) {

		try {
			int i = 0;
			this.write.lock();
			System.out.println("Writing Thread: " + Thread.currentThread().getName()+" value: "+ (value + i++));
			this.cache.put(key++, value + i++);
		} finally {
			this.write.unlock();
		}
	}

	public void read(Long key) {
		try {
			this.read.lock();
			System.out.println("Reading Thread: " + Thread.currentThread().getName()+" value: "+ this.cache.get(key++));
		} finally {
			this.read.unlock();
		}
	}
}

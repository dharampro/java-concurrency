package com.techendear.concurrency.advanced.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
	
	List<Integer> buffer = new ArrayList<Integer>();
	Lock lock = new ReentrantLock();
	
	class Consumer implements Callable<String>{

		@Override
		public String call() throws Exception {
			int count = 0;
			while(count++<50) {
				lock.lock();
				
				while(isEmpty(buffer)) {
					
				}
				
				buffer.remove(buffer.size()-1);
				lock.unlock();
			}
			
			return "Consumed" + (count-1);
		}
		
	}
	
	class Producer implements Callable<String>{

		@Override
		public String call() throws InterruptedException {
			int count = 0;
			
			while(count++<50) {
				try {
				lock.lock();
				while(isFull(buffer)) {
					
				}
				buffer.add(1);
				}finally {
					lock.unlock();
				}
				
						
			}
			return null;
		}
		
	}
}

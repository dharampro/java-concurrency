package com.techendear.concurrency.deadlock;

public class DeadLockMain {

	public static void main(String[] args) throws InterruptedException {
		DeadLockCond cond = new DeadLockCond();
		
		Runnable r1 = ()->cond.a();
		Runnable r2 = ()->cond.b();
		
		Thread t1 = new Thread(r1);
		t1.start();
		
		Thread t2 = new Thread(r2);
		t2.start();
		
		t1.join();
		t2.join();
	}

}

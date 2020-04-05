package com.techendear.concurrency.racecond;

public class RaceCondMain {

	public static void main(String[] args) throws InterruptedException {

		RaceCond cond = new RaceCond(0L);

		Runnable runnable = ()->{
			for(int i=0; i<1_000; i++) {
				cond.setL();
			}
		};
		
		Thread[] threads = new Thread[1_000];
		for(int i=0; i<1_000; i++) {
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		
		for(int i=0; i<1_000; i++) {
			threads[i].join();
		}
		System.out.println(cond.getL());
		
	}

}

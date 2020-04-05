package com.techendear.concurrency.basics;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		int i=0;
		try {
			while(true) {
				Thread.sleep(1000);
				SingletonCls s = SingletonCls.getInstance();
				s.print(this.getClass().getName()+" ITR: "+ i++);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package com.techendear.concurrency.basic;

public class Thread2 implements Runnable {

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

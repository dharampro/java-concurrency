package com.techendear.concurrency.basic;

public class MainClass {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		
		t1.start();
		t2.start();	
	}
}

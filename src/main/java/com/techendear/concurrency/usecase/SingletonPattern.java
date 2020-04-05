package com.techendear.concurrency.usecase;

public class SingletonPattern {

	private static SingletonPattern instance;
	private static Object key = new Object();
	
	public static SingletonPattern getInstance() {
		if(instance != null) {
			return instance;
		}
		
		synchronized (key) {
			instance = new SingletonPattern();
		}
		
		return instance;
	}
}

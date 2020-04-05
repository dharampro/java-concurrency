package com.techendear.concurrency.basics;

public class SingletonCls {

	private static SingletonCls ins;
	
	private SingletonCls() {}
	
	public static synchronized SingletonCls getInstance() {
	
		if(ins == null) {
			ins = new SingletonCls();
		}
		return ins;
	}
	
	public void print(String classname) {
		System.out.println("Lock is with: "+ classname);
	}
}

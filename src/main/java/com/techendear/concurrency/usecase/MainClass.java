package com.techendear.concurrency.usecase;

public class MainClass {

	public static void main(String[] args) {

		SingletonPattern p1 = SingletonPattern.getInstance();
		System.out.println(p1);
		SingletonPattern p2 = SingletonPattern.getInstance();
		System.out.println(p2);
		SingletonPattern p3 = SingletonPattern.getInstance();
		System.out.println(p3);
	}

}

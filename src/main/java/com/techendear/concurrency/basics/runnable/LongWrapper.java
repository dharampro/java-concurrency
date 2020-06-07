package com.techendear.concurrency.basics.runnable;

public class LongWrapper {

	private long l;

	public LongWrapper(long l) {
		this.l = l;
	}

	public long getL() {
		return l;
	}

	public void setL(long l) {
		this.l = l;
	}
	
	public void incrementL() {
		l = l+1;
	}
}

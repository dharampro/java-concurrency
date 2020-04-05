package com.techendear.concurrency.racecondition;

public class RaceCond {

	private Object key = new Object();
	private long l;
	
	public RaceCond(long l) {
		this.l = l;
	}

	public long getL() {
		return l;
	}

	public void setL() {
		synchronized (key) {
			l = l+1;
		}
	}
}

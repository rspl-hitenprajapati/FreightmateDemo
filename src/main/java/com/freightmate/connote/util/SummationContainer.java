package com.freightmate.connote.util;

public class SummationContainer {
	private Long sumEven;
	private Long sumOdd;
	public SummationContainer(Long sumEven, Long sumOdd) {
		super();
		this.sumEven = sumEven;
		this.sumOdd = sumOdd;
	}
	public Long getSumEven() {
		return sumEven;
	}
	public void setSumEven(Long sumEven) {
		this.sumEven = sumEven;
	}
	public Long getSumOdd() {
		return sumOdd;
	}
	public void setSumOdd(Long sumOdd) {
		this.sumOdd = sumOdd;
	}

}

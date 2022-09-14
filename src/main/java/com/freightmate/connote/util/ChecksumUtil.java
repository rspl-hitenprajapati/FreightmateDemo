package com.freightmate.connote.util;

public class ChecksumUtil {
	
	private static long oddsSumConst = 3;
	private static long evenSumConst = 7;

	private ChecksumUtil() {
	}

	public static long calculateChecksum(long nextIndex) {
		long indexToProcess = nextIndex;
		long sumEven = 0;
		long sumOdd = 0;
		int counter = 1;
		while (indexToProcess != 0) {
			long remainder = indexToProcess % 10;
			if (counter % 2 == 0) {
				sumEven = sumEven + remainder;
			} else {
				sumOdd = sumOdd + remainder;
			}
			counter++;
			indexToProcess = indexToProcess / 10;
		}
		sumOdd = sumOdd * oddsSumConst;
		sumEven = sumEven * evenSumConst;
		return nextMultiple(sumEven + sumOdd) - (sumEven + sumOdd);
	}

	private static long nextMultiple(long value) {
		return ((value / 10) + 1) * 10;
	}
}

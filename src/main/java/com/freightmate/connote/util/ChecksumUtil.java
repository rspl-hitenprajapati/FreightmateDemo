package com.freightmate.connote.util;

public class ChecksumUtil {
	
	private static long oddMultiplier = 3;
	private static long evenMultiplier = 7;

	private ChecksumUtil() {
	}

	public static long calculateChecksum(long nextIndex) {
		SummationContainer summationContainer = calculateEvenOddSummation(nextIndex);
		long sumOdd = performMultiplication(summationContainer.getSumOdd(),oddMultiplier);
		long sumEven = performMultiplication(summationContainer.getSumEven(), evenMultiplier);
		long sum = performSummation(sumOdd,sumEven);
		return nextMultipleOf10(sum) - (sum);
	}
	
	
	private static long performSummation(long firstNumber, long secondNumber)
	{
		return firstNumber+secondNumber;
	}
	
	private static long performMultiplication(long sum, long multiplier)
	{
		return sum * multiplier;
	}
	
	
	private static SummationContainer calculateEvenOddSummation(long number)
	{
		long sumEven = 0;
		long sumOdd = 0;
		int counter = 1;
		while (number != 0) {
			long remainder = number % 10;
			if (counter % 2 == 0) {
				sumEven = sumEven + remainder;
			} else {
				sumOdd = sumOdd + remainder;
			}
			counter++;
			number = number / 10;
		}
		
		return new SummationContainer(sumEven, sumOdd);
	}

	private static long nextMultipleOf10(long value) {
		return ((value / 10) + 1) * 10;
	}
}

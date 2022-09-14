package com.freightmate.connote.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChecksumUtilTest {
	
	@Test
	public void testCalculateChecksum_Test1() {
		long input = 19605;
		long actual = ChecksumUtil.calculateChecksum(input);
		long expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCalculateChecksum_Test2() {
		long input = 19321;
		long actual = ChecksumUtil.calculateChecksum(input);
		long expected = 8;
		assertEquals(expected, actual);
	}
}

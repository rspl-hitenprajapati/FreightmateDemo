package com.freightmate.connote.util;

import org.junit.jupiter.api.Test;

public class StringUtilTest {


	@Test
	public void testCreateAbbriviation() {
		System.out.println(StringUtil.createAbbriviation("FreightMateCourierCo"));
	}
	
	@Test
	public void testNumberAppender() {
		System.out.println(StringUtil.numberAppender(15836l, 12));
	}
	
}

package com.freightmate.connote.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringUtilTest {

	@Test
	public void testCreateAbbriviation_InValidCase() {
		String actual = StringUtil.createAbbriviation("carriernameinlowercasenotextracted");
		String expected = "";
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateAbbriviation_ValidCase1() {
		String actual = StringUtil.createAbbriviation("FreightMateCourierCo");
		String expected = "FMCC";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAbbriviation_ValidCase2() {
		String actual = StringUtil.createAbbriviation("NightWareCourierCo");
		String expected = "NWCC";
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
    @CsvSource({
        "carriernameinlowercasenotextracted, ''",
        "FreightMateCourierCo, FMCC"
    })
	public void testCreateAbbriviation_MultipleCases(String input,String expected) {
		String actual = StringUtil.createAbbriviation(input);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberAppender_12DigitCase() {
		String actual = StringUtil.numberAppender(15836l, 12);
		String expected = "000000015836";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberAppender_10DigitCase() {
		String actual = StringUtil.numberAppender(15836l, 10);
		String expected = "0000015836";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberAppender_20DigitCase() {
		String actual = StringUtil.numberAppender(15836l, 20);
		String expected = "00000000000000015836";
		assertEquals(expected, actual);
	}
	
}

package com.freightmate.connote.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;

public class ConsignmentServiceTest {

	private ConsignmentService  consignmentService = new ConsignmentServiceImpl();
	
	@Test
	public void testGenerateConnoteNumber_OutOfRange() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(10);
		connoteNumberRequest.setLastUsedIndex(18201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		IllegalArgumentException illegalArgumentException = assertThrowsExactly(IllegalArgumentException.class,() -> consignmentService.generateConnoteNumber(connoteNumberRequest));
		assertEquals("connote number is not within prescribed range.", illegalArgumentException.getMessage());
	}
	
	@Test
	public void testGenerateConnoteNumber_DigitLimitCase1() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(4);
		connoteNumberRequest.setLastUsedIndex(19201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		IllegalArgumentException illegalArgumentException = assertThrowsExactly(IllegalArgumentException.class,() -> consignmentService.generateConnoteNumber(connoteNumberRequest));
		assertEquals("connote number can not larger then allowed digits.", illegalArgumentException.getMessage());
	}
	
	@Test
	public void testGenerateConnoteNumber_DigitLimitCase2() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(5);
		connoteNumberRequest.setLastUsedIndex(99999l);
		connoteNumberRequest.setRangeStart(99000l);
		connoteNumberRequest.setRangeEnd(100000l);
		IllegalArgumentException illegalArgumentException = assertThrowsExactly(IllegalArgumentException.class,() -> consignmentService.generateConnoteNumber(connoteNumberRequest));
		assertEquals("connote number can not larger then allowed digits.", illegalArgumentException.getMessage());
	}
	
	@Test
	public void testGenerateConnoteNumber_prefixNotExtracted() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("freightmatecourierco");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(10);
		connoteNumberRequest.setLastUsedIndex(19201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		IllegalArgumentException illegalArgumentException = assertThrowsExactly(IllegalArgumentException.class,() -> consignmentService.generateConnoteNumber(connoteNumberRequest));
		assertEquals("Please use camel case in carrierName to use prefix.", illegalArgumentException.getMessage());
	}
	
	@Test
	public void testGenerateConnoteNumber_validCase10Digit() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(10);
		connoteNumberRequest.setLastUsedIndex(19201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		ConnoteNumberResponse actual = consignmentService.generateConnoteNumber(connoteNumberRequest);
		ConnoteNumberResponse expected = new ConnoteNumberResponse("FMCC123ABC00000192022");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGenerateConnoteNumber_validCase20Digit() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(20);
		connoteNumberRequest.setLastUsedIndex(19201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		ConnoteNumberResponse actual = consignmentService.generateConnoteNumber(connoteNumberRequest);
		ConnoteNumberResponse expected = new ConnoteNumberResponse("FMCC123ABC000000000000000192022");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGenerateConnoteNumber_validCaseRangeChange() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(10);
		connoteNumberRequest.setLastUsedIndex(201l);
		connoteNumberRequest.setRangeStart(200l);
		connoteNumberRequest.setRangeEnd(500l);
		ConnoteNumberResponse actual = consignmentService.generateConnoteNumber(connoteNumberRequest);
		ConnoteNumberResponse expected = new ConnoteNumberResponse("FMCC123ABC00000002028");
		assertEquals(expected, actual);
	}
	
}

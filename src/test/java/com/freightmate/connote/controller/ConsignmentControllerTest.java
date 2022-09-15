package com.freightmate.connote.controller;

import static com.freightmate.connote.helper.JsonHelperForTest.objectToJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.freightmate.connote.advice.ErrorResponse;
import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;
import com.freightmate.connote.service.ConsignmentService;

@WebMvcTest(value = ConsignmentController.class)
public class ConsignmentControllerTest {

	@MockBean
	ConsignmentService consignmentService;

	@Autowired
	protected MockMvc mockMvc;
	
	private final String restUrl = "/consignments/connotenumber";

	@Test
	public void testConsignmentController_ValidCase() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		JSONAssert.assertEquals(objectToJson(connoteNumberResponse), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_carrierNameRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setCarrierName(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("carrierName is required.")), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_accountNumberRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setAccountNumber(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("accountNumber is required.")), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_digitRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setDigits(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("digits is required.")), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_lastUsedIndexRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setLastUsedIndex(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("lastUsedIndex is required.")), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_rangeStartRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setRangeStart(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("rangeStart is required.")), result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testConsignmentController_rangeEndRequired() throws Exception {
		ConnoteNumberRequest cannoteRequest = getCannoteRequest();
		cannoteRequest.setRangeEnd(null);
		ConnoteNumberResponse connoteNumberResponse = new ConnoteNumberResponse("FMCC123ABC00000192022");
		when(consignmentService.generateConnoteNumber(Mockito.any())).thenReturn(connoteNumberResponse);
		MvcResult result = mockMvc
				.perform(post(restUrl).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cannoteRequest)))
				.andExpect(status().isBadRequest()).andReturn();
		
		JSONAssert.assertEquals(objectToJson(new ErrorResponse("rangeEnd is required.")), result.getResponse().getContentAsString(), false);
	}
	
	private ConnoteNumberRequest getCannoteRequest() {
		ConnoteNumberRequest connoteNumberRequest = new ConnoteNumberRequest();
		connoteNumberRequest.setCarrierName("FreightMateCourierCo");
		connoteNumberRequest.setAccountNumber("123ABC");
		connoteNumberRequest.setDigits(10);
		connoteNumberRequest.setLastUsedIndex(19201l);
		connoteNumberRequest.setRangeStart(19000l);
		connoteNumberRequest.setRangeEnd(20000l);
		return connoteNumberRequest;
	}
}

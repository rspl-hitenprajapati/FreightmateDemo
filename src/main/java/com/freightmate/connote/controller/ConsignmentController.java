package com.freightmate.connote.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;
import com.freightmate.connote.service.ConsignmentService;

@RestController
@RequestMapping("/consignments")
public class ConsignmentController {
	
	@Autowired
	ConsignmentService consignmentService;

	private static final Logger log = LoggerFactory.getLogger(ConsignmentController.class);

	@PostMapping(value = "/connotenumber", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConnoteNumberResponse> generateConnoteNumber(@Valid @RequestBody ConnoteNumberRequest connoteNumberRequest) throws Exception {
		log.info("ConnoteNumber request received {}", connoteNumberRequest);
		ConnoteNumberResponse connoteNumberResponse =  consignmentService.generateConnoteNumber(connoteNumberRequest);
		log.info("ConnoteNumber sending response {}", connoteNumberResponse);
		return ResponseEntity.status(HttpStatus.OK).body(connoteNumberResponse);
	}
}

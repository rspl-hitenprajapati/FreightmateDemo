package com.freightmate.connote.service;

import javax.validation.Valid;

import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;

public interface ConsignmentService {

	ConnoteNumberResponse generateConnoteNumber(@Valid ConnoteNumberRequest connoteNumberRequest);

}

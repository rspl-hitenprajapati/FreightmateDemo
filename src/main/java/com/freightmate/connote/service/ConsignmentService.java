package com.freightmate.connote.service;

import javax.validation.Valid;

import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;

/**
 * 
 * @author hiten.prajapati
 * @implNote ConsignmentService interface.
 */
public interface ConsignmentService {

	ConnoteNumberResponse generateConnoteNumber(@Valid ConnoteNumberRequest connoteNumberRequest);

}

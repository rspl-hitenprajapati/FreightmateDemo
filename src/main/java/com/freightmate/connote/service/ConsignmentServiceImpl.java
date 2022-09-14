package com.freightmate.connote.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.freightmate.connote.controller.request.ConnoteNumberRequest;
import com.freightmate.connote.controller.request.ConnoteNumberResponse;
import com.freightmate.connote.util.ChecksumUtil;
import com.freightmate.connote.util.StringUtil;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {

	@Override
	public ConnoteNumberResponse generateConnoteNumber(ConnoteNumberRequest connoteNumberRequest) {
		// Validations
		long nextIndex = connoteNumberRequest.getLastUsedIndex() + 1;
		if (nextIndex < connoteNumberRequest.getRangeEnd() || nextIndex > connoteNumberRequest.getRangeStart()) {
			throw new IllegalArgumentException("connote number is not within prescribed range.");
		}
		if (String.valueOf(connoteNumberRequest.getLastUsedIndex() + 1).length() > connoteNumberRequest.getDigits()) {
			throw new IllegalArgumentException("connote number can not larger then allowed digits.");
		}
		String prefix = StringUtil.createAbbriviation(connoteNumberRequest.getCarrierName());
		if (StringUtils.isEmpty(prefix)) {
			throw new IllegalArgumentException("Please use camel case in carrierName to use prefix.");
		}
		// Process
		String nextIndexString = StringUtil.numberAppender(nextIndex, connoteNumberRequest.getDigits());
		long checkSum = ChecksumUtil.calculateChecksum(nextIndex);

		// response
		return new ConnoteNumberResponse(prefix + connoteNumberRequest.getAccountNumber() + nextIndexString + checkSum);
	}

}

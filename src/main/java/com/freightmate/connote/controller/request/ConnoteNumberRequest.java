package com.freightmate.connote.controller.request;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "carrierName", "accountNumber", "digits", "lastUsedIndex", "rangeStart", "rangeEnd" })
public class ConnoteNumberRequest {

	@JsonProperty("carrierName")
	@NotEmpty(message = "carrierName is required.")
	private String carrierName;
	
	@JsonProperty("accountNumber")
	@NotEmpty(message = "accountNumber is required.")
	private String accountNumber;
	
	@JsonProperty("digits")
	@NotNull(message = "digits is required.")
	private Integer digits;
	
	@JsonProperty("lastUsedIndex")
	@NotNull(message = "lastUsedIndex is required.")
	private Long lastUsedIndex;
	
	@JsonProperty("rangeStart")
	@NotNull(message = "rangeStart is required.")
	private Long rangeStart;
	
	@JsonProperty("rangeEnd")
	@NotNull(message = "rangeEnd is required.")
	private Long rangeEnd;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("carrierName")
	public String getCarrierName() {
		return carrierName;
	}

	@JsonProperty("carrierName")
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	@JsonProperty("accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	@JsonProperty("accountNumber")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@JsonProperty("digits")
	public Integer getDigits() {
		return digits;
	}

	@JsonProperty("digits")
	public void setDigits(Integer digits) {
		this.digits = digits;
	}

	@JsonProperty("lastUsedIndex")
	public Long getLastUsedIndex() {
		return lastUsedIndex;
	}

	@JsonProperty("lastUsedIndex")
	public void setLastUsedIndex(Long lastUsedIndex) {
		this.lastUsedIndex = lastUsedIndex;
	}

	@JsonProperty("rangeStart")
	public Long getRangeStart() {
		return rangeStart;
	}

	@JsonProperty("rangeStart")
	public void setRangeStart(Long rangeStart) {
		this.rangeStart = rangeStart;
	}

	@JsonProperty("rangeEnd")
	public Long getRangeEnd() {
		return rangeEnd;
	}

	@JsonProperty("rangeEnd")
	public void setRangeEnd(Long rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "ConnoteNumberRequest [carrierName=" + carrierName + ", accountNumber=" + accountNumber + ", digits="
				+ digits + ", lastUsedIndex=" + lastUsedIndex + ", rangeStart=" + rangeStart + ", rangeEnd=" + rangeEnd
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
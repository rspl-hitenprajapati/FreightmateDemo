package com.freightmate.connote.controller.request;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "connoteNumber"})
public class ConnoteNumberResponse {

	@JsonProperty("connoteNumber")
	private String connoteNumber;
	
	public ConnoteNumberResponse(String connoteNumber) {
		super();
		this.connoteNumber = connoteNumber;
	}

	public String getConnoteNumber() {
		return connoteNumber;
	}

	public void setConnoteNumber(String connoteNumber) {
		this.connoteNumber = connoteNumber;
	}

	@Override
	public String toString() {
		return "ConnoteNumberResponse [connoteNumber=" + connoteNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(connoteNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnoteNumberResponse other = (ConnoteNumberResponse) obj;
		return Objects.equals(connoteNumber, other.connoteNumber);
	}
	
}
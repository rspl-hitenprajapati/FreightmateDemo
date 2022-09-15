package com.freightmate.connote.helper;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public interface JsonHelperForTest {

	static final Logger LOGGER = LoggerFactory.getLogger(JsonHelperForTest.class);

	public static String objectToJson(Object jsonObject) {
		ObjectWriter objectWriter = objectMapper().writer();
		try {
			return objectWriter.writeValueAsString(jsonObject);
		} catch (JsonProcessingException e) {
			LOGGER.error("Failed to convert JSON Object to String.", e);
		}
		return StringUtils.EMPTY;
	}

	public static <T> Optional<T> jsonToObject(String json, Class<T> clazz) {
		try {
			return Optional.of(objectMapper().readValue(json, clazz));
		} catch (IOException e) {
			LOGGER.error("Failed to convert JSON String to Object of specific type.", e);
		}
		return Optional.empty();
	}

	static ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());
		return objectMapper;
	}
}

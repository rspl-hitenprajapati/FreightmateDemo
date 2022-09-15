package com.freightmate.connote.util;

/**
 * 
 * @author hiten.prajapati
 * @implNote StringUtil for operation in strings.
 */
public class StringUtil {

	private StringUtil() {
	}

	public static String createAbbriviation(String name) {
		return name.chars().filter(Character::isUpperCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	public static String numberAppender(long conIndex,int digits) {
		return String.format("%0"+digits+"d", conIndex);
	}
	
}

package com.beet.receipt.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocalDateAttributeConverterTest {
	
	LocalDateAttributeConverter converter;
	
	@BeforeEach
	public void init() {
		converter = new LocalDateAttributeConverter();
	}
	
	@Test
	@DisplayName("Convertion from sql.Date to LocalDate")
	public void convertToDatabaseColumnTest() {
		String date = "2019-10-02";
		Date expected = Date.valueOf(date);
		Date actual = converter.convertToDatabaseColumn(LocalDate.parse(date));
		assertEquals(expected, actual, "Convert LocalDate to sql.Date");
	}
	
	@Test
	public void convertToEntityAttributeTest() {
		String date = "2019-10-02";
		LocalDate expected = LocalDate.parse(date);
		LocalDate actual = converter.convertToEntityAttribute(Date.valueOf(date));
		assertEquals(expected, actual, "Convert sql.Date to LocalDate");
	}
}

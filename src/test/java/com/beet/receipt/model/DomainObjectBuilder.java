package com.beet.receipt.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DomainObjectBuilder {
	
	public static final String account = "account";
	public static final String filename = "filename";
	public static final String originalFileName = "originalFileName";
	public static final String contentType = "image/jpeg";
	public static final String contentText = "Content Text";
	public static final byte[] content = contentText.getBytes();
	public static final LocalDate date = LocalDate.parse("2019-10-07");
	public static final Long amount = new Long(100);
	public static final LocalDateTime dateTime = LocalDateTime.parse("2019-10-07T10:15:30");
	
	public static DomainObjectBuilder aNew() {
        return new DomainObjectBuilder();
    }
	
	public TicketBuilder ticket() {
		return TicketBuilder.create();
	}
	
	public MultiPartFileBuilder multiPartFile() {
		return MultiPartFileBuilder.create();
	}
	
	public ReceiptBuilder receipt() {
		return ReceiptBuilder.create();
	}
	
}

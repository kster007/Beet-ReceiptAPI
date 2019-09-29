package com.beet.receipt.interfaces.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiptDTO {
	private long id;
	private String business;
	private LocalDate date;
	private Long amount;
	private LocalDateTime createdAt;
	private TicketDTO ticket;
	private InvoiceDTO invoice;
	private String status;
}

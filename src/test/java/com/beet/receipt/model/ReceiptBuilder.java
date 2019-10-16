package com.beet.receipt.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.beet.receipt.model.entity.Business;
import com.beet.receipt.model.entity.Invoice;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.model.value.ReceiptStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReceiptBuilder {
	
	private String path;
	private Business business;
	private LocalDate date;
	private Long amount;
	private LocalDateTime createdAt;
	private Ticket ticket;
	private Invoice invoice;
	private ReceiptStatus status;

	public static ReceiptBuilder create() {
		return new ReceiptBuilder(DomainObjectBuilder.account, null, DomainObjectBuilder.date,
				DomainObjectBuilder.amount, DomainObjectBuilder.dateTime, TicketBuilder.create().build(),
				null, ReceiptStatus.IN_PROGRESS);
	}
	
	public Receipt build() {
		return new Receipt(0, path, business, date, amount, createdAt, ticket, invoice, status);
	}
	
}

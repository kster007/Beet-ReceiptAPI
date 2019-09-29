package com.beet.receipt.application;

import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;

public interface ReceiptService {
	
	public Receipt createFromTicket(String prefix, Ticket ticket);
	
	public Receipt findById(String prefix, Long id);
}

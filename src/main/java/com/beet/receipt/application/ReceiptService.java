package com.beet.receipt.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;

public interface ReceiptService {
	
	public Receipt createFromTicket(String account, Ticket ticket);
	public Receipt findById(String account, Long id);
	public Page<Receipt> findByAccount(String account, Pageable pageable);
	
}

package com.beet.receipt.application;


import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.model.entity.Ticket;

public interface TicketService {
	public Ticket createTicket(String prefix, MultipartFile file);
	public void deleteTicket(Ticket ticket);
}

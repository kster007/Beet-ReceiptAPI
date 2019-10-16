package com.beet.receipt.application;


import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.model.entity.Ticket;

public interface TicketService {
	
	public static final String FILE_PREFIX = "TICKET";
	
	public Ticket createTicket(String account, MultipartFile file);
	public void deleteTicket(Ticket ticket);
}

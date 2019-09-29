package com.beet.receipt.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.application.TicketService;

import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.model.value.FileInfo;
import com.beet.receipt.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {
	
	private TicketRepository ticketRepository;
	
	private static final String FILE_PREFIX = "TICKET";
	
	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	
	@Override
	public Ticket createTicket(String prefix, MultipartFile file) {
		Ticket ticket = new Ticket();
		FileInfo fileInfo = FileInfo.generateFileKey(prefix, FILE_PREFIX);
		fileInfo.setName(file.getOriginalFilename());
		fileInfo.setContentType(file.getContentType());
		fileInfo.setSize(file.getSize());
		ticket.setImage(fileInfo);
		return this.ticketRepository.save(ticket);
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		this.ticketRepository.delete(ticket);
	}

}

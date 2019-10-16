package com.beet.receipt.application.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import com.beet.receipt.application.TicketService;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.repository.TicketRepository;

@SpringBootTest
class TicketServiceImplTest {

	@Mock
    private TicketRepository ticketRepository;
	
	@InjectMocks
	TicketService ticketService = new TicketServiceImpl(ticketRepository);
	
	@BeforeEach
	void init() {
		when(this.ticketRepository.save(any(Ticket.class)))
			.then(AdditionalAnswers.returnsFirstArg());
	}
	
	@Test
	void createTicketTest() {
		
		MultipartFile multiPartFile = DomainObjectBuilder.aNew().multiPartFile().build();
		Ticket expected = DomainObjectBuilder.aNew().ticket().build();
		
		Ticket actual = this.ticketService.createTicket(DomainObjectBuilder.account, multiPartFile);
		assertAll("Should compare the components without the UUID", 
			() -> assertEquals(expected.getImage().getName(), actual.getImage().getName()),
			() -> assertEquals(expected.getImage().getContentType(), actual.getImage().getContentType()),
			() -> assertEquals(expected.getImage().getSize(), actual.getImage().getSize()),
			() -> assertTrue(actual.getImage().getKey().matches(DomainObjectBuilder.account + "/" + 
														LocalDate.now() + "/" + TicketService.FILE_PREFIX + ".+"))
		);
		
	}
	
	void deleteTicket() {
		Ticket actual = DomainObjectBuilder.aNew().ticket().build();
		doNothing().when(this.ticketRepository).delete(actual);
		
		this.ticketRepository.delete(actual);
	}
	
	

}

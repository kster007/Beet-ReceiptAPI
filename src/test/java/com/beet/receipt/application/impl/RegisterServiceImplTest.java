package com.beet.receipt.application.impl;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.application.RegisterService;
import com.beet.receipt.application.TicketService;
import com.beet.receipt.exception.InternalException;
import com.beet.receipt.interfaces.service.S3StoreFileClient;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;

import software.amazon.awssdk.core.exception.SdkClientException;

class RegisterServiceImplTest {
		
	private S3StoreFileClient fileStorageService = Mockito.mock(S3StoreFileClient.class);
	private TicketService ticketService = Mockito.mock(TicketService.class);
	private ReceiptService receiptService = Mockito.mock(ReceiptService.class);
	private RegisterService registerService =  new RegisterServiceImpl(this.fileStorageService, this.ticketService, this.receiptService);
	
	@Test
	void registerReceiptFromImageTest() throws Exception {
		
		MultipartFile file =  DomainObjectBuilder.aNew().multiPartFile().build();
		Ticket ticket = DomainObjectBuilder.aNew().ticket().build();
		Receipt expected = DomainObjectBuilder.aNew().receipt().build();
		
		when(this.ticketService.createTicket(DomainObjectBuilder.prefix, file)).thenReturn(ticket);
		doNothing().when(this.fileStorageService).uploadFile(isA(String.class), isA(String.class), any());
		when(this.receiptService.createFromTicket(any(String.class), any(Ticket.class))).thenReturn(expected);
		
		Receipt actual = this.registerService.registerReceiptFromImage(DomainObjectBuilder.prefix, file);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void registerReceiptFromImageExceptionTest() throws Exception {
		MultipartFile file =  DomainObjectBuilder.aNew().multiPartFile().build();
		Ticket ticket = DomainObjectBuilder.aNew().ticket().build();
		
		when(this.ticketService.createTicket(DomainObjectBuilder.prefix, file)).thenReturn(ticket);
		doThrow(SdkClientException.class).when(this.fileStorageService).uploadFile(isA(String.class), isA(String.class), any());
		assertThrows(InternalException.class, () -> this.registerService.registerReceiptFromImage(DomainObjectBuilder.prefix, file), "Any error should had to return an InternalException");
	}

}

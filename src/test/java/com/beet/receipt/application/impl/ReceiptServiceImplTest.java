package com.beet.receipt.application.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.exception.InternalException;
import com.beet.receipt.exception.ValidationException;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.repository.ReceiptRepository;

//@SpringBootTest
class ReceiptServiceImplTest {
	
	//@Mock
	ReceiptRepository receiptRepository = Mockito.mock(ReceiptRepository.class);
	
	//@InjectMocks
	ReceiptService receiptService = new ReceiptServiceImpl(receiptRepository);
	
	
	@Test
	void createFromTicketTest() {
		when(this.receiptRepository.save(any(Receipt.class)))
		.then(AdditionalAnswers.returnsFirstArg());
		
		Receipt expected = DomainObjectBuilder.aNew().receipt().build();
		Ticket t = DomainObjectBuilder.aNew().ticket().build();
		
		Receipt actual = this.receiptService.createFromTicket(DomainObjectBuilder.prefix, t);
		
		assertAll("Should compare the ticket and the status",
			() -> assertEquals(expected.getPath(), DomainObjectBuilder.prefix),
			() -> assertEquals(expected.getStatus(), actual.getStatus()),
			() -> assertNotNull(actual.getTicket())
		); 
		
	}
	
	@Test
	void findByIdTestException() {
		when(receiptRepository.findByIdAndPath(new Long(45), "kster")).thenThrow(RuntimeException.class);
		assertThrows(InternalException.class, ()-> receiptService.findById("kster", new Long(45)), "Any error should had to return an InternalException");
	}
	
	@Test
	void findByIdTestValidationException() {
		when(receiptRepository.findByIdAndPath(new Long(45), "kster")).thenReturn(null);
		assertThrows(ValidationException.class, ()-> receiptService.findById("kster", new Long(45)), "If the receipt does not exist a ValidationException should be throw");
	}
	

}

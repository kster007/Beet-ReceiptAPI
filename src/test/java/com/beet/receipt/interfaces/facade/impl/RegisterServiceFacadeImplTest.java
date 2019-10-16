package com.beet.receipt.interfaces.facade.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.application.RegisterService;
import com.beet.receipt.application.assembler.ReceiptAssembler;
import com.beet.receipt.exception.InternalException;
import com.beet.receipt.interfaces.facade.RegisterServiceFacade;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;

//@SpringBootTest
class RegisterServiceFacadeImplTest {
	
	//@Mock
	RegisterService registerService = Mockito.mock(RegisterService.class);
	
	//@InjectMocks
	RegisterServiceFacade registerServiceFacade = new RegisterServiceFacadeImpl(this.registerService);
	
	@Test
	void registerReceiptFromImageTest() {
		Receipt receipt = DomainObjectBuilder.aNew().receipt().build();
		MultipartFile file = DomainObjectBuilder.aNew().multiPartFile().build();
		doReturn(receipt).when(this.registerService).registerReceiptFromImage(any(String.class), any());
		ResponseEntity<Object> expected = new ResponseEntity<>(ReceiptAssembler.toReceiptDTO(DomainObjectBuilder.prefix,receipt), HttpStatus.CREATED);
		ResponseEntity<Object> actual = this.registerServiceFacade.registerReceiptFromImage(DomainObjectBuilder.prefix, file);
		assertEquals(expected, actual);
	}
	
	@Test
	void registerReceiptFromImageReceiptExceptionTest() {
		
		MultipartFile file = DomainObjectBuilder.aNew().multiPartFile().build();
		doThrow(InternalException.class).when(this.registerService).registerReceiptFromImage(any(String.class),any());
		
		ResponseEntity<Object> actual = this.registerServiceFacade.registerReceiptFromImage(DomainObjectBuilder.prefix, file);
		
		assertAll("In case of an error should had to return a controler error",
				() -> assertEquals(actual.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR)
		);
		
	}
	

}

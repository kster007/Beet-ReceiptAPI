package com.beet.receipt.interfaces.facade.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.application.assembler.ReceiptAssembler;
import com.beet.receipt.interfaces.dto.PageDTO;
import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.facade.ReceiptServiceFacade;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;

class ReceiptServiceFacadeImplTest {

	@Mock
	ReceiptService receiptService = Mockito.mock(ReceiptService.class);
	
	ReceiptServiceFacade receiptServiceFacade = new ReceiptServiceFacadeImpl(receiptService);
	
	@Test
	void getReceiptsTest() {
		Pageable pageable = PageRequest.of(0,3);
		Page<Receipt> page = new PageImpl<>(DomainObjectBuilder.aNew().receiptList(3).build(), pageable ,6);
		
		doReturn(page).when(this.receiptService).findByAccount(any(String.class), any());
		
		PageDTO<ReceiptDTO> pageDto = PageDTO.toPageDTO(page, r -> {
			return ReceiptAssembler.toReceiptDTO(DomainObjectBuilder.account, r);
		});
		ResponseEntity<Object> expected = new ResponseEntity<>(pageDto, HttpStatus.OK);
		ResponseEntity<Object> actual = this.receiptServiceFacade.getReceipts(DomainObjectBuilder.account, pageable);
		
		assertEquals(expected, actual);
	}

}

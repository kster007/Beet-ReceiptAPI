package com.beet.receipt.interfaces.facade;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReceiptServiceFacade {
	
	ResponseEntity<Object> getReceipts(String account, Pageable pageable);
	ResponseEntity<Object> getReceipt(String account, Long id);
	
}

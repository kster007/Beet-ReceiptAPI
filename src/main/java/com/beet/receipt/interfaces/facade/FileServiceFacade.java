package com.beet.receipt.interfaces.facade;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface FileServiceFacade {
	public ResponseEntity<InputStreamResource> downloadTicket(String account, Long receiptId);
}

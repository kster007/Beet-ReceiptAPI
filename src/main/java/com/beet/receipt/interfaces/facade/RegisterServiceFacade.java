package com.beet.receipt.interfaces.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterServiceFacade {
	
	public ResponseEntity<Object> registerReceiptFromImage(String prefix, MultipartFile image);
}

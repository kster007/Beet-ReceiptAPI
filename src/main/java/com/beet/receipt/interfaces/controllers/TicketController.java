package com.beet.receipt.interfaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.interfaces.facade.FileServiceFacade;
import com.beet.receipt.interfaces.facade.RegisterServiceFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	private RegisterServiceFacade registerService;
	private FileServiceFacade receiptService;
	
	@Autowired
	public TicketController(RegisterServiceFacade registerService,  FileServiceFacade receiptService) {
		this.registerService = registerService;
		this.receiptService = receiptService;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/{prefix}")
	public ResponseEntity<Object> createReceipt(@PathVariable String prefix,
												@RequestParam("file") MultipartFile file){
		
		log.info("fileSize: {}, name: {}, type: {}", file.getSize(), file.getOriginalFilename(), file.getContentType());
		
		return this.registerService.registerReceiptFromImage(prefix, file);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{prefix}")
	public ResponseEntity<InputStreamResource> getTicket(@PathVariable String prefix, 
										@RequestParam(name = "receipt", required = true) Long receiptId){
		log.info("receipt: {}", receiptId);
		return this.receiptService.downloadTicket(prefix, receiptId);
	}
}

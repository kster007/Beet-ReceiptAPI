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

import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.facade.FileServiceFacade;
import com.beet.receipt.interfaces.facade.RegisterServiceFacade;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	private RegisterServiceFacade registerService;
	private FileServiceFacade receiptService;
	
	@Autowired
	public TicketController(RegisterServiceFacade registerService,  FileServiceFacade receiptService) {
		this.registerService = registerService;
		this.receiptService = receiptService;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/{account}")
	@ApiOperation(value = "Create a Receipt",
			notes= "Create a new Receipt in the account from a ticket image",
			response = ReceiptDTO.class)
	public ResponseEntity<Object> createReceipt(
			@ApiParam(value = "Account number to register a new receipt", required = true) @PathVariable String account,
												@RequestParam("file") MultipartFile file){
		
		log.info("fileSize: {}, name: {}, type: {}", file.getSize(), file.getOriginalFilename(), file.getContentType());
		
		return this.registerService.registerReceiptFromImage(account, file);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{account}")
	public ResponseEntity<InputStreamResource> getTicket(@PathVariable String account, 
										@RequestParam(name = "receipt", required = true) Long receiptId){
		log.info("receipt: {}", receiptId);
		return this.receiptService.downloadTicket(account, receiptId);
	}
}

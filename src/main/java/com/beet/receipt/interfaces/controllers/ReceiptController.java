package com.beet.receipt.interfaces.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.facade.ReceiptServiceFacade;
import com.beet.receipt.interfaces.utils.PageUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
	
	private ReceiptServiceFacade receiptService;
	
	@Autowired
	public ReceiptController(ReceiptServiceFacade receiptService) {
		this.receiptService = receiptService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{account}")
	@ApiOperation(value = "Return the receipts by page",
	notes= "Return a page of receipts by account number",
	response = ReceiptDTO.class)
	public ResponseEntity<Object> getReceipts(
			@ApiParam(value = "Account number to register a new receipt", required = true) @PathVariable String account,
			Pageable pageable){
		log.info("List Of receipts, account: {}, pageable: {}", account, pageable);
		
		if(pageable == null) 
			pageable = PageUtils.createFirstPage(Arrays.asList(Order.desc("created_at")));
		
		return receiptService.getReceipts(account, pageable);
	}
}

package com.beet.receipt.interfaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beet.receipt.interfaces.facade.BusinessServiceFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/business")
public class BusinessController {
	
	private BusinessServiceFacade businessService;
	
	@Autowired
	public BusinessController(BusinessServiceFacade businessService) {
		this.businessService = businessService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getBusines(){
		return this.businessService.getBusiness();
	}
}

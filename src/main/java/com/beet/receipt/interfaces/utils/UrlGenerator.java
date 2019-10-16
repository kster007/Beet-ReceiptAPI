package com.beet.receipt.interfaces.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

public interface UrlGenerator {

	static String TICKET_URL_BASE = "/api/ticket/{account}";
	
	static String generateTicketFromReceipt(String account, Long receiptId) {
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("account", account);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(TICKET_URL_BASE)
				.queryParam("receipt", receiptId);
		
		return builder.buildAndExpand(urlParams).toUriString();
	}
	
}

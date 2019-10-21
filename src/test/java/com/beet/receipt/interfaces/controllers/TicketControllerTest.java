package com.beet.receipt.interfaces.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.service.S3StoreFileClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TicketControllerTest {
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private S3StoreFileClient s3StoreFileClient;
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	void createReceiptTest() throws Exception {
		LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.add("file", new org.springframework.core.io.ClassPathResource("ticket.jpeg"));
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(parameters, headers);
	    
	    ResponseEntity<ReceiptDTO> response = this.restTemplate.exchange(
	    		new URL("http://localhost:" + port + "/api/ticket/kster").toString(), 
	    		HttpMethod.POST, entity, ReceiptDTO.class);
	    
	    assertAll("the response for the service should be a receipt",
	    		() -> assertEquals(response.getStatusCode(), HttpStatus.CREATED),
	    		() -> assertNotNull(response.getBody().getCreatedAt()),
	    		() -> assertEquals(response.getBody().getStatus(), "IN_PROGRESS"),
	    		() -> assertEquals("/api/ticket/kster?receipt=" + response.getBody().getId(), response.getBody().getTicket().getUrl())
	    );
	}

}

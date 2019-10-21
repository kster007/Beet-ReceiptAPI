package com.beet.receipt.interfaces.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.beet.receipt.interfaces.dto.PageDTO;
import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.model.DomainObjectBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReceiptControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	void test() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder
			.fromUri(new URI("http://localhost:" + port + "/api/receipt/" + DomainObjectBuilder.account))
			.queryParam("page", 0)
			.queryParam("size", 3);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<PageDTO<ReceiptDTO>> response = this.restTemplate.exchange(
				builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<PageDTO<ReceiptDTO>>() {});
		assertAll("", 
			() -> assertEquals( HttpStatus.OK ,response.getStatusCode()),
			() -> assertEquals(6, response.getBody().getTotalElements()),
			() -> assertEquals(2, response.getBody().getTotalPages()),
			() -> assertNotNull(response.getBody().getData()),
			() -> assertEquals(3, response.getBody().getData().size())
		);
		
	}

}

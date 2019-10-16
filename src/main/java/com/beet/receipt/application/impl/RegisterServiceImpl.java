package com.beet.receipt.application.impl;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.application.RegisterService;
import com.beet.receipt.application.TicketService;
import com.beet.receipt.exception.InternalException;
import com.beet.receipt.interfaces.service.S3StoreFileClient;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {
	
	private S3StoreFileClient fileStorageService;
	private TicketService ticketService;
	private ReceiptService receiptService;
	public static final String BUCKET_NAME = "beet-dev-receipts";
	
	@Autowired
	public RegisterServiceImpl(S3StoreFileClient fileStorageService, TicketService ticketService,
			ReceiptService receiptService) {
		this.fileStorageService = fileStorageService;
		this.ticketService = ticketService;
		this.receiptService = receiptService;
	}

	@Override
	public Receipt registerReceiptFromImage(String account, MultipartFile image) {
		Ticket ticket = this.ticketService.createTicket(account, image);
		try {
			this.fileStorageService.uploadFile(BUCKET_NAME, ticket.getImage().getKey(), image.getResource());
		} catch (AwsServiceException | SdkClientException | URISyntaxException | IOException e) {
			log.error("Ticket: {}" + e.getMessage(), ticket, e);
			this.ticketService.deleteTicket(ticket);
			throw new InternalException();
		}
		return this.receiptService.createFromTicket(account, ticket);
	}

}

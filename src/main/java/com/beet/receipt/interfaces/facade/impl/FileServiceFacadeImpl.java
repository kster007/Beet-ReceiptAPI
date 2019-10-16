package com.beet.receipt.interfaces.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.application.impl.RegisterServiceImpl;
import com.beet.receipt.exception.ReceiptException;
import com.beet.receipt.interfaces.facade.FileServiceFacade;
import com.beet.receipt.interfaces.service.S3StoreFileClient;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.value.FileInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceFacadeImpl implements FileServiceFacade {

	private ReceiptService receiptService;
	private S3StoreFileClient fileStorageService;
	
	@Autowired
	public FileServiceFacadeImpl(ReceiptService receiptService, S3StoreFileClient fileStorageService){
		this.receiptService = receiptService;
		this.fileStorageService = fileStorageService;
	}
	
	@Override
	public ResponseEntity<InputStreamResource> downloadTicket(String account, Long receiptId) {
		try {
			Receipt receipt = this.receiptService.findById(account, receiptId);
			return this.download(receipt.getTicket().getImage());
		}catch (ReceiptException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}
	
	private ResponseEntity<InputStreamResource> download(FileInfo fileInfo){
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "filename=" + fileInfo.getName());
			return ResponseEntity.ok()
					.headers(headers)
					.contentLength(fileInfo.getSize())
					.contentType(MediaType.parseMediaType(fileInfo.getContentType()))
					.body(this.fileStorageService.downloadFile(RegisterServiceImpl.BUCKET_NAME,fileInfo.getKey()));
		}catch(Exception e) {
			log.error("fileInfo: {}", fileInfo, e);
			return ResponseEntity.notFound().build();
		}
	}

}

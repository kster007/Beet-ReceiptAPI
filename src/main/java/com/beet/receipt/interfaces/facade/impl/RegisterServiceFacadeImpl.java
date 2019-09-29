package com.beet.receipt.interfaces.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.application.RegisterService;
import com.beet.receipt.application.assembler.FileAssembler;
import com.beet.receipt.application.assembler.ReceiptAssembler;
import com.beet.receipt.interfaces.dto.FileDTO;
import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.facade.RegisterServiceFacade;
import com.beet.receipt.interfaces.utils.ManageResponse;
import com.beet.receipt.model.entity.Receipt;

@Service
public class RegisterServiceFacadeImpl implements RegisterServiceFacade {

	private RegisterService registerService;
	
	
	@Autowired
	public RegisterServiceFacadeImpl (RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@Override
	public ResponseEntity<Object> registerReceiptFromImage(String prefix, MultipartFile image) {
		
		FileDTO file = FileAssembler.toFIleDTO(prefix, image);
		
		ManageResponse<FileDTO, ReceiptDTO> manageResponse = new ManageResponse<>(HttpStatus.CREATED,
			f -> { 
				Receipt r = this.registerService.registerReceiptFromImage(f.getPrefix(), f.getFile());
				return ReceiptAssembler.toReceiptDTO(r);
			} 
		); 
		
		return manageResponse.build(file);
	}

}

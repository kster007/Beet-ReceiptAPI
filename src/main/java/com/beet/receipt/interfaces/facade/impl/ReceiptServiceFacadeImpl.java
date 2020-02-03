package com.beet.receipt.interfaces.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.application.assembler.ReceiptAssembler;
import com.beet.receipt.interfaces.dto.PageDTO;
import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.facade.ReceiptServiceFacade;
import com.beet.receipt.interfaces.utils.ManageResponse;
import com.beet.receipt.model.entity.Receipt;

@Service
public class ReceiptServiceFacadeImpl implements ReceiptServiceFacade {
	
	private ReceiptService receiptService;
	
	@Autowired
	public ReceiptServiceFacadeImpl(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@Override
	public ResponseEntity<Object> getReceipts(final String account, Pageable pageable) {
		ManageResponse<Pageable, PageDTO<ReceiptDTO>> manageResponse = new ManageResponse<>(HttpStatus.OK,
			p-> {
				Page<Receipt> page = this.receiptService.findByAccount(account, p);
				return PageDTO.toPageDTO(page, r -> {
					return ReceiptAssembler.toReceiptDTO(account, r);
				});
			});
		
		return manageResponse.build(pageable);
	}

	@Override
	public ResponseEntity<Object> getReceipt(String account, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

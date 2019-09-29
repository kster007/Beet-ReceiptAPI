package com.beet.receipt.application.impl;

import org.springframework.stereotype.Service;

import com.beet.receipt.application.ReceiptService;
import com.beet.receipt.exception.InternalException;
import com.beet.receipt.exception.ReceiptException;
import com.beet.receipt.exception.ValidationException;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.model.value.ReceiptStatus;
import com.beet.receipt.repository.ReceiptRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

	private ReceiptRepository receiptRepository;
	
	public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
		this.receiptRepository = receiptRepository;
	}
	
	@Override
	public Receipt createFromTicket(String prefix, Ticket ticket) {
		
		Receipt receipt = new Receipt();
		receipt.setPath(prefix);
		receipt.setStatus(ReceiptStatus.IN_PROGRESS);
		receipt.setTicket(ticket);
		Receipt ret = this.receiptRepository.save(receipt);
		
		return ret;
	}

	@Override
	public Receipt findById(String prefix, Long id) throws ReceiptException {	
		try {
			Receipt r = this.receiptRepository.findByIdAndPath(id, prefix);
			if(r == null)
				throw new ValidationException("Receipt not found");
			return r;
		}catch(ValidationException e) {
			log.error("prefix: {} id: {}" +  id, prefix, e);
			throw e;
		}catch(Exception e) {
			log.error("prefix: {} id: {}" + e.getMessage(), id, prefix, e);
			throw new InternalException();
		}
	}

}

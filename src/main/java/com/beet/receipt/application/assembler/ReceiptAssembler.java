package com.beet.receipt.application.assembler;

import com.beet.receipt.interfaces.dto.InvoiceDTO;
import com.beet.receipt.interfaces.dto.ReceiptDTO;
import com.beet.receipt.interfaces.dto.TicketDTO;
import com.beet.receipt.model.entity.Invoice;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.model.entity.Ticket;

public interface ReceiptAssembler {
	static ReceiptDTO toReceiptDTO(Receipt receipt) {
		ReceiptDTO ret = null;
		
		if(receipt != null) {
			ret = new ReceiptDTO();
			ret.setId(receipt.getId());
			if(receipt.getBusiness() != null) {
				ret.setBusiness(receipt.getBusiness().getName());
			}
			ret.setDate(receipt.getDate());
			ret.setAmount(receipt.getAmount());
			ret.setCreatedAt(receipt.getCreatedAt());
			ret.setTicket(ReceiptAssembler.toTicketDTO(receipt.getTicket()));
			ret.setInvoice(ReceiptAssembler.toInvoiceDTO(receipt.getInvoice()));
			ret.setStatus(receipt.getStatus().toString());
		}
		
		return ret;
	}
	
	static TicketDTO toTicketDTO(Ticket ticket) {
		TicketDTO ret= null;

		

		return ret;
	}

	static InvoiceDTO toInvoiceDTO(Invoice invoice){
		InvoiceDTO ret = null;

		return ret;
	}

}

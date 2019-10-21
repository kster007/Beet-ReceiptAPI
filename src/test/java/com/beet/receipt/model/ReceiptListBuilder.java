package com.beet.receipt.model;

import java.util.Collections;
import java.util.List;

import com.beet.receipt.model.entity.Receipt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReceiptListBuilder {
	
	private List<Receipt> receipts;
	
	public static ReceiptListBuilder create(int size) {
		return new ReceiptListBuilder(Collections.nCopies(size, DomainObjectBuilder.aNew().receipt().build()));
	}
	
	public List<Receipt> build(){
		return receipts;
	}
}

package com.beet.receipt.model;

import com.beet.receipt.model.entity.Ticket;
import com.beet.receipt.model.value.FileInfo;

public class TicketBuilder {
	
	private FileInfo fileInfo;
	
	public static TicketBuilder create() {
		return new TicketBuilder( FileInfoBuilder.create().build() );
	}
	
	public TicketBuilder(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public Ticket build() {
		return new Ticket(0, this.fileInfo);
	}

}

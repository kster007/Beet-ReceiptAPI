package com.beet.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;
import com.beet.receipt.repository.ReceiptRepository;
import com.beet.receipt.repository.TicketRepository;

@Component
@Profile({"test"})
public class SetupInitialData implements CommandLineRunner {
	
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Receipt r = DomainObjectBuilder.aNew().receipt().build();
		this.ticketRepository.save(r.getTicket());
		for(long i = 1; i <= 12 ; i++) {
			r.setId(i);
			this.receiptRepository.save(r);
			if(i == 6)
				r.setAccount("account2");
		}

	}

}

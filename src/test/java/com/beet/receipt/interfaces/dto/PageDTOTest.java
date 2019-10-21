package com.beet.receipt.interfaces.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.beet.receipt.application.assembler.ReceiptAssembler;
import com.beet.receipt.model.DomainObjectBuilder;
import com.beet.receipt.model.entity.Receipt;

class PageDTOTest {

	@Test
	void test() {
		Function<Receipt, ReceiptDTO> converter = r -> ReceiptAssembler.toReceiptDTO(DomainObjectBuilder.account , r);
		PageDTO<ReceiptDTO> expected = new PageDTO<>();
		expected.setTotalElements(6);
		expected.setTotalPages(2);
		List<Receipt> receipts = DomainObjectBuilder.aNew().receiptList(3).build();
		expected.setData(receipts.stream().map(converter).collect(Collectors.toList()));
		
		Page<Receipt> page = new PageImpl<>(receipts, PageRequest.of(0,3),6);
		
		PageDTO<ReceiptDTO> actual = PageDTO.toPageDTO(page, converter);
		
		assertEquals(expected, actual);
		
	}

}

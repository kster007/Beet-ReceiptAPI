package com.beet.receipt.interfaces.utils;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public interface PageUtils {
	
	static Pageable createFirstPage(List<Order> orders) {
		return PageRequest.of(0,30,Sort.by(orders));
	}
}

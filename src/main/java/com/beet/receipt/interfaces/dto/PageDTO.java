package com.beet.receipt.interfaces.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageDTO<T> {
	
	private long totalPages;
	private long totalElements;
	private List<T> data;
	
	public static <T,U> PageDTO<T> toPageDTO(Page<U> page, Function<U,T> f){
		PageDTO<T> ret = new PageDTO<>();
		ret.setTotalElements(page.getTotalElements());
		ret.setTotalPages(page.getTotalPages());
		ret.setData(page.get().map(f).collect(Collectors.toList()));
		return ret;
	}

}

package com.beet.receipt.application;

import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.model.entity.Receipt;

public interface RegisterService {
	public Receipt registerReceiptFromImage(String account, MultipartFile image);
}

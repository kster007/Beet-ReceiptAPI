package com.beet.receipt.interfaces.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDTO {
	private String prefix;
	private MultipartFile file;
}

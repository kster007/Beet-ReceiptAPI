package com.beet.receipt.application.assembler;

import org.springframework.web.multipart.MultipartFile;

import com.beet.receipt.interfaces.dto.FileDTO;

public interface FileAssembler {

	static FileDTO toFIleDTO(String account, MultipartFile file) {
		FileDTO ret = new FileDTO();
		ret.setPrefix(account);
		ret.setFile(file);
		return ret;
	}
}

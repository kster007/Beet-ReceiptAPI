package com.beet.receipt.model;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MultiPartFileBuilder {
	
	private String filename;
	private String originalFileName;
	private String contentType;
	private byte[] content;
	
	public static MultiPartFileBuilder create() {
		return new MultiPartFileBuilder(DomainObjectBuilder.filename, 
				DomainObjectBuilder.originalFileName, 
				DomainObjectBuilder.contentType, 
				DomainObjectBuilder.content);
	}
	
	public MultipartFile build() {
		return new MockMultipartFile(
				this.filename, 
				this.originalFileName, 
				this.contentType, 
				this.content);
	}

}

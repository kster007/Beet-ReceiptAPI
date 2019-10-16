package com.beet.receipt.model;

import com.beet.receipt.application.TicketService;
import com.beet.receipt.model.value.FileInfo;

public class FileInfoBuilder {

	private String name;
	private String contentType;
	private Long size;
	
	public static FileInfoBuilder create() {
		return new FileInfoBuilder(
				DomainObjectBuilder.originalFileName,
				DomainObjectBuilder.contentType,
				(long) DomainObjectBuilder.content.length
				);
	}
	
	public FileInfoBuilder(String name, String contentType, Long size) {
		this.name = name;
		this.contentType = contentType;
		this.size = size;
	}
	
	public FileInfo build() {
		FileInfo fi = FileInfo.generateFileKey(DomainObjectBuilder.prefix, TicketService.FILE_PREFIX);
		fi.setContentType(contentType);
		fi.setName(this.name);
		fi.setSize(this.size);
		return fi;
	}
	
}

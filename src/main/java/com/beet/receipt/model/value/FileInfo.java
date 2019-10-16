package com.beet.receipt.model.value;

import com.beet.receipt.model.utils.FileKeyGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
	private String key;
	private String name;
	private String contentType;
	private Long size;
	
	public static FileInfo generateFileKey( String prefix, String filePrefix ) {
		FileInfo fileKey = new FileInfo();
		fileKey.setKey(FileKeyGenerator.generateKey(prefix, filePrefix));
		return fileKey;
	}
}

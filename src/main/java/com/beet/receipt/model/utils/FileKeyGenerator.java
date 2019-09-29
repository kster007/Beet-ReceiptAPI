package com.beet.receipt.model.utils;

import java.time.LocalDate;
import java.util.UUID;

public interface FileKeyGenerator {
	
	static String PATH_SEPARATOR = "/";
	static String FILE_NAME_SEPARATOR = "_";
	
	static String generateKey(String prefix, String filePrefix, LocalDate date, UUID uuid) {
		return prefix + PATH_SEPARATOR  +  date.toString() + PATH_SEPARATOR + filePrefix + FILE_NAME_SEPARATOR + uuid; 
	}
	
	static String generateKey(String prefix, String filePrefix, UUID uuid) {
		return FileKeyGenerator.generateKey(prefix, filePrefix, LocalDate.now(), uuid);
	}
	
	static String generateKey(String prefix, String filePrefix, LocalDate date) {
		return FileKeyGenerator.generateKey(prefix, filePrefix, date, UUID.randomUUID());
	}
	
	static String generateKey(String prefix, String filePrefix) {
		return FileKeyGenerator.generateKey(prefix, filePrefix, LocalDate.now(), UUID.randomUUID());
	}
	
}

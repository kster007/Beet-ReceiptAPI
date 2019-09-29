package com.beet.receipt.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beet.receipt.model.value.FileInfo;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "invoice")
@ToString(exclude = {"id"}, includeFieldNames = false)
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_id")
	private long id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name= "key", column=@Column(name= "xml_key", nullable = false, length = 255)),
		@AttributeOverride(name= "name", column=@Column(name= "xml_name", nullable = false, length = 255)),
		@AttributeOverride(name= "contentType", column=@Column(name= "xml_content_type", nullable = false, length = 255)),
		@AttributeOverride(name= "size", column=@Column(name= "xml_size", nullable = false, precision = 20))
	})
	private FileInfo xml;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name= "key", column=@Column(name= "pdf_key", nullable = true, length = 255)),
		@AttributeOverride(name= "name", column=@Column(name= "pdf_name", nullable = false, length = 255)),
		@AttributeOverride(name= "contentType", column=@Column(name= "pdf_content_type", nullable = false, length = 255)),
		@AttributeOverride(name= "size", column=@Column(name= "pdf_size", nullable = false, precision = 20))
	})
	private FileInfo pdf;
	
	
	
}

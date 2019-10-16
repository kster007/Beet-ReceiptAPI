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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="ticket")
@Data
@ToString(exclude = {"id"}, includeFieldNames = false)
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_id")
	private long id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name= "key", column=@Column(name= "image_key", nullable = false, length = 255)),
		@AttributeOverride(name= "name", column=@Column(name= "image_name", nullable = false, length = 255)),
		@AttributeOverride(name= "contentType", column=@Column(name= "image_content_type", nullable = false, length = 255)),
		@AttributeOverride(name= "size", column=@Column(name= "image_size", nullable = false, precision = 20))
		
	})
	private FileInfo image;
	
}

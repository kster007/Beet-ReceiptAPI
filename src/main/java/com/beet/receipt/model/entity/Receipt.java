package com.beet.receipt.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.beet.receipt.model.value.ReceiptStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "receipt")
@ToString(exclude = {"id"})
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="receipt_id")
	private long id;
	
	@Column(name="path", nullable = false)
	private String path;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="business_id")
	private Business business;
	
	@Column(name = "date", nullable = true)
	private LocalDate date;
	
	@Column(name = "amount", nullable = true, precision = 20)
	private Long amount;
	
	@CreatedDate
	@Column(name= "created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = true)
	@JoinColumn(name="ticket_id", nullable = true)
	private Ticket ticket;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	@Basic
    @Enumerated(EnumType.STRING)
	@Column(name= "status")
	private ReceiptStatus status;
	
}

package com.beet.receipt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beet.receipt.model.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
	Receipt findByIdAndAccount(Long id, String account);
	Page<Receipt> findByAccount(String account, Pageable pageable);
}

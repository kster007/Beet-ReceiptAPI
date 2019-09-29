package com.beet.receipt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beet.receipt.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

package com.beet.receipt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beet.receipt.model.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

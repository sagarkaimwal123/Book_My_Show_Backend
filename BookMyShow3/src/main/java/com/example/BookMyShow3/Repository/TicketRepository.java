package com.example.BookMyShow3.Repository;

import com.example.BookMyShow3.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
}

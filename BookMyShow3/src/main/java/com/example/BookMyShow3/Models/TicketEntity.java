package com.example.BookMyShow3.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String alloted_seats;
    private int amount;
    private Date booked_at;
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeatEntity>bookedSeats;

}

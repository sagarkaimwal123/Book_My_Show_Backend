package com.example.BookMyShow3.Models;

import com.example.BookMyShow3.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;

@Entity
@Table(name="theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private String address;
    //List of shows

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<ShowEntity>listOfShows;
    //List of theater seats
    @OneToMany(mappedBy = "theater",cascade =CascadeType.ALL)
    private List<TheaterSeatEntity>theaterSeatEntityList;

}

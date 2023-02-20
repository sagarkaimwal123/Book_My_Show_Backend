package com.example.BookMyShow3.Dtos;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
public class BookTicketRequestDto {
    private List<String>requestSeats;
    private int showId;
    private int userId;
}

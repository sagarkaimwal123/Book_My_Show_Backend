package com.example.BookMyShow3.Service;

import com.example.BookMyShow3.Dtos.BookTicketRequestDto;
import com.example.BookMyShow3.Models.ShowEntity;
import com.example.BookMyShow3.Models.ShowSeatEntity;
import com.example.BookMyShow3.Models.TicketEntity;
import com.example.BookMyShow3.Models.UserEntity;
import com.example.BookMyShow3.Repository.ShowRepository;
import com.example.BookMyShow3.Repository.TicketRepository;
import com.example.BookMyShow3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequestDto bookTicketRequestDto)throws Exception
    {
        //Get the request
        List<String> requestedSeats=bookTicketRequestDto.getRequestSeats();
        ShowEntity showEntity=showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity=userRepository.findById(bookTicketRequestDto.getUserId()).get();
        //Get the showSeats from showEntity
        List<ShowSeatEntity>showSeats=showEntity.getListOfSeats();


        //Validte if I can allcate these seats to the requested by user.

        List<ShowSeatEntity>bookedSeats=new ArrayList<>();
        for(ShowSeatEntity showSeat:showSeats)
        {
            String seatNo=showSeat.getSeatNo();
            if(showSeat.isBooked()==false&&requestedSeats.contains(seatNo))
            {
                bookedSeats.add(showSeat);
            }
        }

        //FAILURE CASE
        if(bookedSeats.size()!=requestedSeats.size())
        {
            throw new Exception("Requested seats are not available");
        }

        //SUCCESS CASE
        //This means the bookedSeats actually contains the booked seats.
        TicketEntity ticketEntity=new TicketEntity();
        double totalAmount=0;
        double multiplier=showEntity.getMultiplier();


        String allotedSeats="";

        int rate=0;
        //Calculating amount ,setting bookedStatus,setting
        for(ShowSeatEntity bookedSeat:bookedSeats)
        {
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo=bookedSeat.getSeatNo();

            allotedSeats=allotedSeats+seatNo+",";
            if(seatNo.charAt(0)=='1')rate=100;
            else rate=200;

            totalAmount=totalAmount+multiplier*rate;
        }
        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bidirectional mapping part is pending
        ticketRepository.save(ticketEntity);
        return "Successfully created a ticket";
    }
}

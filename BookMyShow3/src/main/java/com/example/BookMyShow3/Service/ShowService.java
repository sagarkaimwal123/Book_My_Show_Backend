package com.example.BookMyShow3.Service;

import com.example.BookMyShow3.Dtos.ShowRequestDto;
import com.example.BookMyShow3.Models.*;
import com.example.BookMyShow3.Repository.MovieRepository;
import com.example.BookMyShow3.Repository.ShowRepository;
import com.example.BookMyShow3.Repository.ShowSeatRepository;
import com.example.BookMyShow3.Repository.TheaterRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowRequestDto showRequestDto)
    {
        //show Entity
        ShowEntity showEntity =ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();
        //You need to get the movieRepo
        MovieEntity movieEntity=movieRepository.findByMovieName(showRequestDto.getMovieName());
        //YOu need to get the theater Repository
        TheaterEntity theaterEntity=theaterRepository.findById(showRequestDto.getTheaterId()).get();
        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        //bcz we are doing a bideirectional mapping:
        //Optional things:
        // List<ShowEntity>currentListOfShow=movieEntity.getListOfShows();
        //currentListOfShow.add(showEntity);
        //movieEntity.setListOfShows(currentListOfShow);
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        //theaterRepository.save(theaterEntity);



        List<ShowSeatEntity> seatEntityList=createShowSeats(theaterEntity.getTheaterSeatEntityList());
        showEntity.setListOfSeats(seatEntityList);
        //for each showseat :we need to mark that to which showit belongs (foreign key wiil be filled)
        for(ShowSeatEntity showSeat:seatEntityList)
        {
            showSeat.setShow(showEntity);
        }
        //showRepository.save(showEntity);this doesn't neet to be called bcz parent save function is being called
        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
        return "Show added successfully";
    }
    public List<ShowSeatEntity>createShowSeats(List<TheaterSeatEntity>theaterSeatEntityList)
    {
        List<ShowSeatEntity>seats=new ArrayList<>();
        for(TheaterSeatEntity theaterSeat:theaterSeatEntityList)
        {
            ShowSeatEntity showSeat=ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }
        showSeatRepository.saveAll(seats);
        return seats;
    }
}

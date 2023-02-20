package com.example.BookMyShow3.Controllers;

import com.example.BookMyShow3.Dtos.TheaterRequestDto;
import com.example.BookMyShow3.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto)
    {
        return theaterService.createTheater(theaterRequestDto);

    }
}

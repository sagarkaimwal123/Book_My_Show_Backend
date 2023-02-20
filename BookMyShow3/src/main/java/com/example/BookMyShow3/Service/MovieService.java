package com.example.BookMyShow3.Service;

import com.example.BookMyShow3.Dtos.MovieRequestDto;
import com.example.BookMyShow3.Models.MovieEntity;
import com.example.BookMyShow3.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieRequestDto movieRequestDto)
    {
        MovieEntity movie=MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        movieRepository.save(movie);
        return "Movie added successfully";
    }
}

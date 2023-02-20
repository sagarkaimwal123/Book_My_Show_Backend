package com.example.BookMyShow3.Repository;

import com.example.BookMyShow3.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
    MovieEntity findByMovieName(String movieName);
}

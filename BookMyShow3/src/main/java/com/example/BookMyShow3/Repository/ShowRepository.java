package com.example.BookMyShow3.Repository;

import com.example.BookMyShow3.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
}

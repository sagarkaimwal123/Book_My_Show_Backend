package com.example.BookMyShow3.Repository;

import com.example.BookMyShow3.Models.TheaterEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
    TheaterEntity findByNameAndCity(String name,String city);
}

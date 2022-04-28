package com.training.springbootdata.repository;

import com.training.springbootdata.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAirportRepository extends JpaRepository<Airport, String> {

    List<Airport> findAll();

    Optional<Airport> findById(String iata);

}

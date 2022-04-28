package com.training.springbootdata.service;

import com.training.springbootdata.entity.Airport;
import com.training.springbootdata.repository.IAirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl {

    @Autowired
    private IAirportRepository repository;

    public List<Airport> getAllAirports(){
        return repository.findAll();
    }

    public Optional<Airport> findAirport(String iata){ return repository.findById(iata);}
}

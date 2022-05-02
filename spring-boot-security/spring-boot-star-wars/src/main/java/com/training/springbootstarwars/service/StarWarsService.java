package com.training.springbootstarwars.service;

import com.training.springbootstarwars.dto.StarWarsVehiclesResultDto;
import com.training.springbootstarwars.properties.PropertiesConfig;
import com.training.springbootstarwars.proxy.HttpProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class StarWarsService {

    @Autowired
    PropertiesConfig config;

    @Autowired
    HttpProxy httpProxy;

    public Mono<StarWarsVehiclesResultDto> getStarWarsVehicles() throws MalformedURLException{
           return httpProxy.getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class);
    }

}

package com.example.flight.airline.repository;

import com.example.flight.airline.entity.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IAirlineDao  extends CrudRepository<Airline, Long> {
    ArrayList<Airline> findAll();
    boolean existsByAirlineCode(String airlineCode);

}

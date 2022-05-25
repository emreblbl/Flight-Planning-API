package com.example.flight.airport.repository;

import com.example.flight.airport.entity.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirportDao extends CrudRepository<Airport,Integer> {

}

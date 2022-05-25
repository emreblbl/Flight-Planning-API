package com.example.flight.flight.repository;

import com.example.flight.flight.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightDao extends CrudRepository<Flight,Integer> {
}

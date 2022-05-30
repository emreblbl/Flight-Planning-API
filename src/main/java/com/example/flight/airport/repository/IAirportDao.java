package com.example.flight.airport.repository;

import com.example.flight.airport.entity.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAirportDao extends CrudRepository<Airport,Integer> {
    Optional<Airport> findByAirportCode(String airportCode);
    Optional<Airport> deleteByAirportCode(String airportCode);
    @Query(value = "SELECT a from Airport a where " +
            "a.airportCode IN (:airportCodes)")
    List<Airport> findAirportByAirportCode(@Param("airportCodes") List<String> airportCodes);


}

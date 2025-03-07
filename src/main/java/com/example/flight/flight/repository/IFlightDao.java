package com.example.flight.flight.repository;

import com.example.flight.flight.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface IFlightDao extends CrudRepository<Flight, Long> {
    @Query(" select f from Flight f where ((f.destinationAirportCode = :sourceAirportCode" +
            " and f.sourceAirportCode = :destinationAirportCode) " +
            "or (f.destinationAirportCode = :destinationAirportCode" +
            " and f.sourceAirportCode = :sourceAirportCode)) " +
            "and (f.airlineCode = :airlineCode) and (f.flightDate=:date)")
    List<Flight> customFindAllByDestCodeAndSourceCodeAndAirlineCode(@Param("destinationAirportCode")String destCode
            , @Param("sourceAirportCode")String sourceCode
            , @Param("airlineCode")String airlineCode
            , LocalDate date);
    ArrayList<Flight> findAll();
}

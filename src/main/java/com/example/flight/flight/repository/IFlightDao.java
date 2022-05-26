package com.example.flight.flight.repository;

import com.example.flight.flight.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IFlightDao extends CrudRepository<Flight,Long> {

    @Query(" select f from Flight f where f.destinationAirportCode = :destinationAirportCode and f.sourceAirportCode = :sourceAirportCode and f.airlineCode = :airlineCode")
    List<Flight> customFindAllByDestCodeAndSourceCodeAndAirlineCode(@Param("destinationAirportCode")String destCode,@Param("sourceAirportCode")String sourceCode,@Param("airlineCode")String airlineCode);
    ArrayList<Flight> findAll();
}

package com.example.flight.flight.service.Imp;

import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.model.request.FlightInsertRequestVO;
import com.example.flight.flight.model.request.FlightUpdateRequestVO;
import com.example.flight.flight.model.response.FlightResponseDto;

public class FlightEntityBinder {

    public static final void updateEntityFromUpdateRequestVO(FlightUpdateRequestVO flightUpdateRequestVO, Flight flight){
        flight.setFlightDate(flightUpdateRequestVO.getFlightDate());
        flight.setFlightTime(flightUpdateRequestVO.getFlightTime());
        flight.setAirlineCode(flightUpdateRequestVO.getAirlineCode());
        flight.setDestinationAirportCode(flightUpdateRequestVO.getDestinationAirportCode());
        flight.setSourceAirportCode(flightUpdateRequestVO.getSourceAirportCode());
    }
    public static final Flight convertRequestVOToEntity(FlightInsertRequestVO flightInsertRequestVO){
        Flight flight = Flight.builder().airlineCode(flightInsertRequestVO.getAirlineCode())
                .sourceAirportCode(flightInsertRequestVO.getSourceAirportCode())
                .destinationAirportCode(flightInsertRequestVO.getDestinationAirportCode())
                .flightDate(flightInsertRequestVO.getFlightDate())
                .flightTime(flightInsertRequestVO.getFlightTime()).build();
        return flight;
    }
    public static final FlightResponseDto convertToDto(Flight flight){
        FlightResponseDto flightResponseDto = FlightResponseDto.builder().id(flight.getId())
                .airlineCode(flight.getAirlineCode())
                .sourceAirportCode(flight.getSourceAirportCode())
                .destinationAirportCode(flight.getDestinationAirportCode())
                .flightDate(flight.getFlightDate())
                .flightTime(flight.getFlightTime()).build();
        return flightResponseDto;

    }
}

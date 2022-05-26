package com.example.flight.flight.service;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.model.FlightRequestVO;
import org.springframework.http.ResponseEntity;



public interface IFlightService {
    ResponseEntity<BaseResponseVO> updateFlight(FlightRequestVO flightRequestVO);
    ResponseEntity<BaseResponseVO> insertFlight(Flight flight);
    ResponseEntity<BaseResponseVO> deleteFlight(Long flightId);
    ResponseEntity<BaseResponseVO> getFlightById(Long flightId);
    ResponseEntity<BaseResponseVO> getFlightList();

}

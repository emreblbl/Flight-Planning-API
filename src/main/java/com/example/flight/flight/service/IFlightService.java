package com.example.flight.flight.service;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.flight.entity.Flight;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;


public interface IFlightService {
    ResponseEntity<BaseResponseVO> updateFlight(Flight flight);
    ResponseEntity<BaseResponseVO> insertFlight(Flight flight);
    ResponseEntity<BaseResponseVO> deleteFlight(String flightId);
    ResponseEntity<BaseResponseVO> getFlightById(String flightId);
    ResponseEntity<BaseResponseVO> getFlightList();

}

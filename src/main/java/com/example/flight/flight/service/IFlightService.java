package com.example.flight.flight.service;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.flight.model.request.FlightInsertRequestVO;
import com.example.flight.flight.model.request.FlightUpdateRequestVO;
import org.springframework.http.ResponseEntity;



public interface IFlightService {
    ResponseEntity<BaseResponseVO> updateFlight(FlightUpdateRequestVO flightUpdateRequestVO);
    ResponseEntity<BaseResponseVO> insertFlight(FlightInsertRequestVO flightInsertRequestVO);
    ResponseEntity<BaseResponseVO> deleteFlight(Long flightId);
    ResponseEntity<BaseResponseVO> getFlightById(Long flightId);
    ResponseEntity<BaseResponseVO> getFlightList();

}

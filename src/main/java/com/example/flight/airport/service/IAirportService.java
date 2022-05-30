package com.example.flight.airport.service;

import com.example.flight.airport.entity.Airport;
import com.example.flight.base.model.BaseResponseVO;
import org.springframework.http.ResponseEntity;


public interface IAirportService {
    ResponseEntity<BaseResponseVO> insert(Airport airport);
    ResponseEntity<BaseResponseVO> getByAirportCode(String airportCode);
    ResponseEntity<BaseResponseVO> delete(String airportCode);
    ResponseEntity<BaseResponseVO> getList();
}

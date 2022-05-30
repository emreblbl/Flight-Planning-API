package com.example.flight.airline.service;


import com.example.flight.airline.entity.Airline;
import com.example.flight.base.model.BaseResponseVO;
import org.springframework.http.ResponseEntity;


public interface IAirlineService {
    ResponseEntity<BaseResponseVO> insertAirline(Airline airline);
    ResponseEntity<BaseResponseVO> delete(Long airlineId);
    ResponseEntity<BaseResponseVO> getAirline();

}

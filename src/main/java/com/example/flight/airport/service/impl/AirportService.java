package com.example.flight.airport.service.impl;

import com.example.flight.airport.entity.Airport;
import com.example.flight.airport.repository.IAirportDao;
import com.example.flight.airport.service.IAirportService;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import com.example.flight.base.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AirportService implements IAirportService {
    private final IAirportDao iAirportDao;
    @Autowired
    public AirportService(IAirportDao iAirportDao){
        this.iAirportDao = iAirportDao;
    }
    @Override
    public ResponseEntity<BaseResponseVO> insert(Airport airport) {
        airport = iAirportDao.save(airport);
        if(airport.getId() !=null){
            return ResponseHelper.getSuccessResponse(airport, ServiceMessage.INSERT_SUCCESS);
        }else {
            return null;// do nothing just for using illustrate flight scenario
        }
    }


    @Override
    public ResponseEntity<BaseResponseVO> getByAirportCode(String airportCode) {
        Optional<Airport> airportOptional = iAirportDao.findByAirportCode(airportCode);
        Airport airport = airportOptional.isPresent() == true ? airportOptional.get() :null;
        if(airport !=null){
            return ResponseHelper.getSuccessResponse(airport, ServiceMessage.GET_SUCCESS);

        }else{
            return null;// do nothing just for using illustrate flight scenario

        }

    }

    @Override
    public ResponseEntity<BaseResponseVO> delete(String airportCode) {
        Optional<Airport> airport = iAirportDao.deleteByAirportCode(airportCode);
        if(airport.isPresent()){
            return ResponseHelper.getSuccessResponse(airport, ServiceMessage.DELETE_SUCCESS);

        }else{
            return null;// do nothing just for using illustrate flight scenario
        }

    }

    @Override
    public ResponseEntity<BaseResponseVO> getList() {
        return ResponseHelper.getSuccessResponse(iAirportDao.findAll(),ServiceMessage.GET_SUCCESS);
    }
}

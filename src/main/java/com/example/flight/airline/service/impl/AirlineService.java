package com.example.flight.airline.service.impl;

import com.example.flight.airline.entity.Airline;
import com.example.flight.airline.repository.IAirlineDao;
import com.example.flight.airline.service.IAirlineService;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import com.example.flight.base.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AirlineService implements IAirlineService {
    private final IAirlineDao iAirlineDao;
    @Autowired
    public AirlineService(IAirlineDao iAirlineDao){
        this.iAirlineDao = iAirlineDao;
    }
    @Override
    public ResponseEntity<BaseResponseVO> insertAirline(Airline airline) {
        airline = iAirlineDao.save(airline);
        if(airline !=null){
            return ResponseHelper.getSuccessResponse(airline, ServiceMessage.INSERT_SUCCESS);
        }else{
            return null;// do nothing just for using illustrate flight scenario
        }

    }

    @Override
    public ResponseEntity<BaseResponseVO> delete(Long airlineId) {
        iAirlineDao.deleteById(airlineId);
        if(iAirlineDao.existsById(airlineId)){
            return null;// do nothing just for using illustrate flight scenario

        }else{
            return ResponseHelper.getSuccessResponse(ServiceMessage.DELETE_SUCCESS);

        }

    }

    @Override
    public ResponseEntity<BaseResponseVO> getAirline() {
        ArrayList<Airline> airlineArrayList = iAirlineDao.findAll();
        if(!airlineArrayList.isEmpty()){
            return ResponseHelper.getSuccessResponse(airlineArrayList, ServiceMessage.GET_SUCCESS);
        }else{
            return null;// do nothing just for using illustrate flight scenario
        }

    }
}

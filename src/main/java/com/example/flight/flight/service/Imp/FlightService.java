package com.example.flight.flight.service.Imp;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.service.IFlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public class FlightService implements IFlightService {
    @Override
    public ResponseEntity<BaseResponseVO> updateFlight(Flight flight) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> insertFlight(Flight flight) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> deleteFlight(String flightId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightById(String flightId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightList() {
        return null;
    }
}

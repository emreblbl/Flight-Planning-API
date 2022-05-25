package com.example.flight.flight.controller;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/flight")
public class FlightController {

    private final IFlightService iFlightService;
    @Autowired
    public FlightController(IFlightService iFlightService){
        this.iFlightService =iFlightService;
    }
    @PostMapping("")
    public ResponseEntity<BaseResponseVO> insertFlight(@Valid @RequestBody Flight flight){
        return iFlightService.insertFlight(flight);
    }
    @PutMapping("")
    public ResponseEntity<BaseResponseVO> updateFlight(@Valid @RequestBody Flight flight){
        return iFlightService.updateFlight(flight);
    }
    @DeleteMapping("/{flightId}")
    public ResponseEntity<BaseResponseVO> deleteFlight(@Valid @PathVariable String flightId){
        return iFlightService.deleteFlight(flightId);
    }
    @GetMapping("")
    public ResponseEntity<BaseResponseVO> getFlight(){
        return iFlightService.getFlightList();
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<BaseResponseVO> getFlight(@PathVariable String flightId){
        return iFlightService.getFlightById(flightId);
    }

}

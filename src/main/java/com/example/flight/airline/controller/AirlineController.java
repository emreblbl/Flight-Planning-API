package com.example.flight.airline.controller;

import com.example.flight.airline.entity.Airline;
import com.example.flight.airline.service.IAirlineService;
import com.example.flight.base.model.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(path = "/airline")
public class AirlineController {
    private final IAirlineService iAirlineService;
    @Autowired
    public AirlineController(IAirlineService iAirlineService){
        this.iAirlineService = iAirlineService;
    }
    @PostMapping("")
    public ResponseEntity<BaseResponseVO> insertAirline(@Valid @RequestBody Airline airline){
        return iAirlineService.insertAirline(airline);
    }
    @DeleteMapping("/{airlineId}")
    public ResponseEntity<BaseResponseVO> deleteFlight(@PathVariable Long airlineId){
        return iAirlineService.delete(airlineId);
    }
    @GetMapping("")
    public ResponseEntity<BaseResponseVO> getFlight(){
        return iAirlineService.getAirline();
    }
}

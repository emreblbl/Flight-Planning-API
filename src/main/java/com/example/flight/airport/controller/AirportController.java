package com.example.flight.airport.controller;

import com.example.flight.airport.entity.Airport;
import com.example.flight.airport.service.IAirportService;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.constant.ValidationMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/airport")
@Validated
public class AirportController {
    private final IAirportService iAirportService;
    @Autowired
    public AirportController(IAirportService iAirportService){
        this.iAirportService =iAirportService;
    }
    @PostMapping("")
    public ResponseEntity<BaseResponseVO> insert(@Valid @RequestBody Airport airport){
        return iAirportService.insert(airport);
    }
    @GetMapping("")
    public ResponseEntity<BaseResponseVO> get( @Size(min = 3,max = 3,message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
                                                   @NotNull(message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
                                                   @RequestParam("airport code") String airportCode){
        return iAirportService.getByAirportCode(airportCode);
    }
    @GetMapping("/list")
    public ResponseEntity<BaseResponseVO> getList(){
        return iAirportService.getList();
    }
    @DeleteMapping("")
    public ResponseEntity<BaseResponseVO> delete(@Size(min = 3,max = 3,message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
                                                     @NotNull(message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
                                                     @RequestParam("airport code")String airportCode){
        return iAirportService.delete(airportCode);

    }

}

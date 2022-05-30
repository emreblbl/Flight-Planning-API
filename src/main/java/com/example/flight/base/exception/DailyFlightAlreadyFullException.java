package com.example.flight.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DailyFlightAlreadyFullException extends RuntimeException{
    public DailyFlightAlreadyFullException(LocalDate date
            ,String airlineCode
            ,String sourceAirportCode
            ,String destAirportCode ){
        super(String.format("%s airline firm already complete daily flight in %s between %s and %s "
                +"Please change date for flight.",
        airlineCode,date.toString(),sourceAirportCode,destAirportCode));
    }
}

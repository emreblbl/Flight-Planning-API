package com.example.flight.flight.model.response;

import com.example.flight.base.constant.DateAndTimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResponseDto {
    private Long id;
    private String airlineCode;
    private String sourceAirportCode;
    private String destinationAirportCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DateAndTimeFormatConstant.DATE_FORMAT)
    private LocalDate flightDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DateAndTimeFormatConstant.TIME_FORMAT)
    private LocalTime flightTime;
}

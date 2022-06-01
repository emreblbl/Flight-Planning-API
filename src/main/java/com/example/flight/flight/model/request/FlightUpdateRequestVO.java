package com.example.flight.flight.model.request;


import com.example.flight.base.constant.DateAndTimeFormatConstant;
import com.example.flight.base.constant.ValidationMessageConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FlightUpdateRequestVO {
    @NotNull
    private Long id;
    @NotNull(message = ValidationMessageConstant.AIRLINE_MANDATORY_MESSAGE)
    @Size(min = 2,max = 2,message = ValidationMessageConstant.AIRLINE_MANDATORY_MESSAGE)
    private String airlineCode;
    @NotNull(message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
    @Size(min = 3,max = 3,message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
    private String sourceAirportCode;
    @NotNull(message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
    @Size(min = 3,max = 3,message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
    private String destinationAirportCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DateAndTimeFormatConstant.DATE_FORMAT)
    @NotNull
    private LocalDate flightDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DateAndTimeFormatConstant.TIME_FORMAT)
    private LocalTime flightTime;
}

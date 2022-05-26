package com.example.flight.flight.entity;

import com.example.flight.base.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @Size(min = 2,max = 2,message = Constant.AIRLINE_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRLINE_MANDATORY_MESSAGE)
    private String airlineCode;
    @Size(min = 3,max = 3,message = Constant.AIRPORT_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRPORT_MANDATORY_MESSAGE)
    private String sourceAirportCode;
    @Size(min = 3,max = 3,message = Constant.AIRPORT_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRPORT_MANDATORY_MESSAGE)
    private String destinationAirportCode;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern =Constant.DATE_FORMAT)
    @NotNull
    private Date flightDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern =Constant.TIME_FORMAT)
    @Temporal(TemporalType.TIME)
    private Date flightTime;
    @CreationTimestamp
    @JsonIgnore
    private Date createAt;
    @UpdateTimestamp
    @JsonIgnore
    private Date updateAt;
    public Flight(Long id,String airlineCode,String sourceAirportCode,String destinationAirportCode,Date flightDate,Date flightTime){
        this.id = id;
        this.airlineCode=airlineCode;
        this.sourceAirportCode=sourceAirportCode;
        this.destinationAirportCode=destinationAirportCode;
        this.flightDate=flightDate;
        this.flightTime=flightTime;
    }



}

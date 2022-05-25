package com.example.flight.flight.entity;

import com.example.flight.base.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(generator = Constant.UUID_STRATEGY)
    @GenericGenerator(name = Constant.UUID_STRATEGY, strategy = Constant.UUID_STRATEGY)
    @JsonIgnore
    private String id;
    @Size(min = 2,max = 2,message = Constant.AIRLINE_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRLINE_MANDATORY_MESSAGE)
    private String airlineCode;
    @Size(min = 3,max = 3,message = Constant.AIRPORT_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRPORT_MANDATORY_MESSAGE)
    private String sourceAirportCode;
    @Size(min = 3,max = 3,message = Constant.AIRPORT_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRPORT_MANDATORY_MESSAGE)
    private String destinationAirportCode;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern =Constant.DATE_FORMAT)
    @NotNull
    private Date flightDate;
    @NotNull
    private Time flightTime;
    @CreationTimestamp
    @JsonIgnore
    private Date createAt;
    @UpdateTimestamp
    @JsonIgnore
    private Date updateAt;


}

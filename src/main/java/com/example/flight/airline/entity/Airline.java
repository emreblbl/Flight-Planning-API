package com.example.flight.airline.entity;

import com.example.flight.base.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @NotNull(message = "airline name is mandotary")
    private String airlineName;
    @Size(min = 2,max = 2,message = Constant.AIRLINE_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRLINE_NAME_MANDATORY_MESSAGE)
    private String airlineCode;// IAATA standard


}

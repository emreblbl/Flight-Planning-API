package com.example.flight.airline.entity;

import com.example.flight.base.constant.ValidationMessageConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "airline name is mandotary")
    private String airlineName;
    @NotNull
    @Size(min = 2,max = 2,message = ValidationMessageConstant.AIRLINE_MANDATORY_MESSAGE)
    private String airlineCode;// IAATA standard


}

package com.example.flight.airline.entity;

import com.example.flight.base.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(generator = Constant.UUID_STRATEGY)
    @GenericGenerator(name = Constant.UUID_STRATEGY, strategy = Constant.UUID_STRATEGY)
    @JsonIgnore
    private String id;
    @NotNull(message = "airline name is mandotary")
    private String airlineName;
    @Size(min = 2,max = 2,message = "IATA Code must be 2 characters.")
    @NotNull(message = "airline code is mandotary")
    private String airlineCode;// IAATA standard


}

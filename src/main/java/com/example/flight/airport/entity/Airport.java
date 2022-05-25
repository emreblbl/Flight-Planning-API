package com.example.flight.airport.entity;
import com.example.flight.base.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(generator = Constant.UUID_STRATEGY)
    @GenericGenerator(name = Constant.UUID_STRATEGY, strategy = Constant.UUID_STRATEGY)
    @JsonIgnore
    private String id;
    @NotNull(message = Constant.AIRPORT_NAME_MANDATORY_MESSAGE)
    private String airportName;
    @Size(min = 3,max = 3,message = Constant.AIRPORT_MANDATORY_MESSAGE)
    @NotNull(message = Constant.AIRPORT_MANDATORY_MESSAGE)
    private String airportCode; // IATA code : ISB(Islamad airport)

}

package com.example.flight.airport.entity;
import com.example.flight.base.constant.KeyHelperConstant;
import com.example.flight.base.constant.ValidationMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
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
public class Airport {
    @Id
    @GeneratedValue(generator = KeyHelperConstant.UUID_STRATEGY)
    @GenericGenerator(name = KeyHelperConstant.UUID_STRATEGY, strategy = KeyHelperConstant.UUID_STRATEGY)
    private String id;
    @NotBlank(message = ValidationMessageConstant.AIRPORT_NAME_MANDATORY_MESSAGE)
    private String airportName;
    @NotNull
    @Size(min = 3,max = 3,message = ValidationMessageConstant.AIRPORT_MANDATORY_MESSAGE)
    private String airportCode; // IATA code : ISB(Islamad airport)

}

package com.example.flight.flight.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    private String airlineCode;
    private String sourceAirportCode;
    private String destinationAirportCode;
    private LocalDate flightDate;
    private LocalTime flightTime;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Version
    private Long version;
}

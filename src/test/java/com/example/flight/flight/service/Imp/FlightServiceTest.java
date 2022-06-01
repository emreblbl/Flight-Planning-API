package com.example.flight.flight.service.Imp;

import com.example.flight.airline.repository.IAirlineDao;
import com.example.flight.airport.entity.Airport;
import com.example.flight.airport.repository.IAirportDao;
import com.example.flight.base.exception.EntityNotFoundException;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import com.example.flight.base.util.ResponseHelper;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.model.request.FlightInsertRequestVO;
import com.example.flight.flight.model.response.FlightResponseDto;
import com.example.flight.flight.repository.IFlightDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
class FlightServiceTest {
    @InjectMocks
    private FlightService flightService;
    @Mock
    private IFlightDao iFlightDao;
    @Mock
    private IAirlineDao iAirlineDao;
    @Mock
    private IAirportDao iAirportDao;
    FlightInsertRequestVO flightInsertRequestVO;
    Flight flight;
    Flight flightFromDb;
    FlightResponseDto flightResponseDto;
    ResponseEntity<BaseResponseVO> desiredResult;
    @BeforeEach
    public void setUp(){

        flightInsertRequestVO = FlightInsertRequestVO.builder().
                flightDate(LocalDate.parse("2022-05-17")).sourceAirportCode("AAA")
                .destinationAirportCode("BBB")
                .flightTime(LocalTime.parse("15:30:00"))
                .airlineCode("TA").build();
        flight = Flight.builder().flightDate(LocalDate.parse("2022-05-17")).sourceAirportCode("AAA")
                .destinationAirportCode("BBB")
                .flightTime(LocalTime.parse("15:30:00"))
                .airlineCode("TA").build();
        flightFromDb = Flight.builder()
                .id(1L).
                flightDate(LocalDate.parse("2022-05-17"))
                .sourceAirportCode("AAA")
                .destinationAirportCode("BBB")
                .flightTime(LocalTime.parse("15:30:00"))
                .airlineCode("TA").build();
        flightResponseDto = FlightResponseDto.builder().flightDate(LocalDate.parse("2022-05-17"))
                .sourceAirportCode("AAA")
                .destinationAirportCode("BBB")
                .flightTime(LocalTime.parse("15:30:00"))
                .airlineCode("TA").build();
    }
    @Test
    @DisplayName("When insert flight called with valid request, It should return valid FlightDto")
    public void whenInsertFlightCalledWithValidRequest_itShouldReturnValidFlightDto() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(flight.getSourceAirportCode());
        arrayList.add(flight.getDestinationAirportCode());
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport());
        airportList.add(new Airport());
        Assertions.assertFalse(arrayList.isEmpty());
        Assertions.assertEquals(2,airportList.size());
        Assertions.assertEquals(2, arrayList.size());
        desiredResult = ResponseHelper
                .getSuccessResponse(flightResponseDto
                        , ServiceMessage.INSERT_SUCCESS);

        // pre-condition :
        when(iAirportDao.findAirportByAirportCode(arrayList)).thenReturn(2);
        when(iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(
                flightInsertRequestVO.getDestinationAirportCode(),
                flightInsertRequestVO.getSourceAirportCode()
                , flightInsertRequestVO.getAirlineCode()
                , flightInsertRequestVO.getFlightDate())).thenReturn(Arrays.asList(flightFromDb));
        when(iAirlineDao.existsByAirlineCode(flight.getAirlineCode())).thenReturn(true);
        when(iFlightDao.save(flight)).thenReturn(flightFromDb);
        //act
        ResponseEntity<BaseResponseVO> result =flightService.insertFlight(flightInsertRequestVO);

        // assert
        Assertions.assertEquals(desiredResult,result);
        //verify

        verify(iAirportDao).findAirportByAirportCode(arrayList);
        verify(iFlightDao).customFindAllByDestCodeAndSourceCodeAndAirlineCode(
                flightInsertRequestVO.getDestinationAirportCode()
                ,flightInsertRequestVO.getSourceAirportCode()
                ,flightInsertRequestVO.getAirlineCode()
                ,flightInsertRequestVO.getFlightDate());
        verify(iAirlineDao).existsByAirlineCode(flight.getAirlineCode());
        verify(iFlightDao).save(flight);


    }

    @Test()
    @DisplayName("when insert flight called with unexist airlinecode, it should thrown EntityNotFoundException")
    public void whenInsertFlightCalledWithNonExistAirlinecode_ItShouldReturnThrownEntityNotFoundException() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(flight.getSourceAirportCode());
        arrayList.add(flight.getDestinationAirportCode());
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport());
        airportList.add(new Airport());



        when(iAirlineDao.existsByAirlineCode(flight.getAirlineCode())).thenReturn(false);
        when(iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(
                flightInsertRequestVO.getDestinationAirportCode(),
                flightInsertRequestVO.getSourceAirportCode()
                , flightInsertRequestVO.getAirlineCode()
                , flightInsertRequestVO.getFlightDate())).thenReturn(Arrays.asList(flightFromDb));
        when(iFlightDao.save(flight)).thenReturn(flightFromDb);
        //act
        Assertions.assertThrows(EntityNotFoundException.class,()->{
            flightService.insertFlight(flightInsertRequestVO);
        });

        verify(iAirlineDao).existsByAirlineCode(flight.getAirlineCode());

    }


}
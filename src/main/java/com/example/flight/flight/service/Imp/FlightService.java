package com.example.flight.flight.service.Imp;

import com.example.flight.airline.entity.Airline;
import com.example.flight.airline.repository.IAirlineDao;
import com.example.flight.airport.entity.Airport;
import com.example.flight.airport.repository.IAirportDao;
import com.example.flight.base.exception.DailyFlightAlreadyFullException;
import com.example.flight.base.exception.EntityNotFoundException;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import com.example.flight.base.util.ResponseHelper;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.model.request.FlightInsertRequestVO;
import com.example.flight.flight.model.request.FlightUpdateRequestVO;
import com.example.flight.flight.model.response.FlightResponseDto;
import com.example.flight.flight.repository.IFlightDao;
import com.example.flight.flight.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService implements IFlightService {
    private final IFlightDao iFlightDao;
    private final IAirlineDao iAirlineDao;
    private final IAirportDao iAirportDao;
    @Autowired
    public FlightService(IAirportDao iAirportDao,IAirlineDao iAirlineDao,IFlightDao iFlightDao){
        this.iFlightDao = iFlightDao;
        this.iAirlineDao = iAirlineDao;
        this.iAirportDao =iAirportDao;
    }
    @Override
    public ResponseEntity<BaseResponseVO> insertFlight(FlightInsertRequestVO flightInsertRequestVO) {
        Flight flight = FlightRequestBinder.convertRequestVOToEntity(flightInsertRequestVO);
        checkAirlineAndAirport(
                flight.getAirlineCode()
                ,flight.getSourceAirportCode()
                ,flight.getDestinationAirportCode());
        List<Flight> flightList = iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(
                flight.getDestinationAirportCode()
                ,flight.getSourceAirportCode()
                ,flight.getAirlineCode()
                ,flight.getFlightDate());
        if(flightList.size()<3){
            flight = iFlightDao.save(flight);
            if(flight.getId()!=null){
                FlightResponseDto flightResponseDto= FlightRequestBinder.convertToDto(flight);
                return ResponseHelper.getSuccessResponse(flightResponseDto, ServiceMessage.INSERT_SUCCESS);

            }else{
                throw new DataIntegrityViolationException("Something wrong with persistance layer when writing/updating into database.");
            }

        }else{
            throw  new DailyFlightAlreadyFullException( flight.getFlightDate()
                    ,flight.getAirlineCode()
                    ,flight.getSourceAirportCode()
                    ,flight.getDestinationAirportCode());
        }
    }
    private void checkAirlineAndAirport(String airlineCode, String sourceAirportCode, String destinationAirportCode){
        if(!iAirlineDao.existsByAirlineCode(airlineCode)){
            throw new EntityNotFoundException(Airline.class,"airlineCode",airlineCode);
        }
        ArrayList<String> airportCodeList = new ArrayList<>();
        airportCodeList.add(sourceAirportCode);
        airportCodeList.add(destinationAirportCode);
        List<Airport> airportList = iAirportDao.findAirportByAirportCode(airportCodeList);
        if(airportList.size() !=2){
            throw new EntityNotFoundException(Airport.class,"airportCode",sourceAirportCode.concat(" or "+destinationAirportCode));
        }
    }

    @Override
    public ResponseEntity<BaseResponseVO> updateFlight(FlightUpdateRequestVO flightUpdateRequestVO) {
        Optional<Flight> flightOptional = iFlightDao.findById(flightUpdateRequestVO.getId());
        Flight flight = flightOptional.isPresent() == true ? flightOptional.get() :null;
        if(flight !=null){
            FlightRequestBinder.setFlightRequestVOOnEntity(flightUpdateRequestVO,flight);
        }else{
            throw new EntityNotFoundException(Flight.class,"flight Id",flightUpdateRequestVO.getId().toString());
        }
        checkAirlineAndAirport(flight.getAirlineCode()
                ,flight.getSourceAirportCode()
                ,flight.getDestinationAirportCode());
        List<Flight> flightList = iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(
                flight.getDestinationAirportCode()
                ,flight.getSourceAirportCode()
                ,flight.getAirlineCode()
                ,flight.getFlightDate());
        // when user update a flight,we should ignore flight that we are updating in that day.
        for(int i =0;i<flightList.size();i++){
            if(flightList.get(i).getId() ==flight.getId()){
                flightList.remove(flightList.get(i));
            }
        }
        if(flightList.size()<3){
            Flight savedflight = iFlightDao.save(flight);
            if(savedflight.getId()!=null){
                return ResponseHelper.getSuccessResponse(FlightRequestBinder.convertToDto(savedflight), ServiceMessage.INSERT_SUCCESS);

            }else{
                throw new DataIntegrityViolationException("Something wrong with persistance layer when writing/updating into database.");
            }

        }
        throw  new DailyFlightAlreadyFullException( flight.getFlightDate()
                ,flight.getAirlineCode()
                ,flight.getSourceAirportCode()
                ,flight.getDestinationAirportCode());
    }

    @Override
    public ResponseEntity<BaseResponseVO> deleteFlight(Long flightId) {
        iFlightDao.deleteById(flightId);
        if(!iFlightDao.existsById(flightId)){
            return ResponseHelper.getSuccessResponse("Delete successful", ServiceMessage.DELETE_SUCCESS);
        }else{
            throw new EntityNotFoundException(Flight.class,"flightId",flightId.toString());

        }
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightById(Long flightId) {
        Optional<Flight> flightOptional = iFlightDao.findById(flightId);
        Flight flight = flightOptional.isPresent() == true ? flightOptional.get() :null;
        if(flight !=null){
            return ResponseHelper.getSuccessResponse(flight, ServiceMessage.GET_SUCCESS);
        }else{
            throw new EntityNotFoundException(Flight.class,"flightId",flightId.toString());
        }
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightList() {
        ArrayList<Flight> airlineArrayList = iFlightDao.findAll();
        if(!airlineArrayList.isEmpty()){
            ArrayList<FlightResponseDto> flightResponseDtos = new ArrayList<>();
            airlineArrayList.forEach(flight -> {
                flightResponseDtos.add(FlightRequestBinder.convertToDto(flight));
            });
            return ResponseHelper.getSuccessResponse(flightResponseDtos, ServiceMessage.GET_SUCCESS);
        }else{
            throw new EntityNotFoundException(Flight.class);
        }
    }
}

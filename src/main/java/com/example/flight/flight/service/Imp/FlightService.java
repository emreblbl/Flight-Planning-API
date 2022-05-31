package com.example.flight.flight.service.Imp;

import com.example.flight.airline.entity.Airline;
import com.example.flight.airline.repository.IAirlineDao;
import com.example.flight.airport.entity.Airport;
import com.example.flight.airport.repository.IAirportDao;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
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
    @Transactional
    public ResponseEntity<BaseResponseVO> insertFlight(FlightInsertRequestVO flightInsertRequestVO) {
        Flight flight = FlightEntityBinder.convertRequestVOToEntity(flightInsertRequestVO);
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
            return ResponseHelper.getSuccessResponse(FlightEntityBinder.convertToDto(flight), ServiceMessage.INSERT_SUCCESS);
        }else{
            return ResponseHelper.getSuccessResponse(String.format("%s airline firm already complete " +
                            "daily flight in %s between %s and %s "
                                    +"Please change date for flight.",
                     flight.getAirlineCode()
                    ,flight.getFlightDate().toString()
                    ,flight.getSourceAirportCode()
                    ,flight.getDestinationAirportCode()),ServiceMessage.INSERT_FAILED);
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
    @Transactional
    public ResponseEntity<BaseResponseVO> updateFlight(FlightUpdateRequestVO flightUpdateRequestVO) {
        Optional<Flight> flightOptional = iFlightDao.findById(flightUpdateRequestVO.getId());
        Flight flight = flightOptional.isPresent() == true ? flightOptional.get() :null;
        if(flight !=null){
            FlightEntityBinder.setFlightRequestVOOnEntity(flightUpdateRequestVO,flight);
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
            if(flightList.get(i).getId() == flight.getId()){
                flightList.remove(flightList.get(i));
                break;
            }
        }
        if(flightList.size()<3){
            Flight savedflight = iFlightDao.save(flight);
            return ResponseHelper.getSuccessResponse(FlightEntityBinder.convertToDto(savedflight), ServiceMessage.UPDATE_SUCCESS);
        }
        return ResponseHelper.getSuccessResponse(String.format("%s airline firm already complete " +
                        "daily flight in %s between %s and %s "
                        +"Please change date for flight.",
                flight.getAirlineCode()
                ,flight.getFlightDate().toString()
                ,flight.getSourceAirportCode()
                ,flight.getDestinationAirportCode()),ServiceMessage.UPDATE_FAILED);


    }

    @Override
    @Transactional
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
            return ResponseHelper.getSuccessResponse(FlightEntityBinder.convertToDto(flight), ServiceMessage.GET_SUCCESS);
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
                flightResponseDtos.add(FlightEntityBinder.convertToDto(flight));
            });
            return ResponseHelper.getSuccessResponse(flightResponseDtos, ServiceMessage.GET_SUCCESS);
        }else{
            throw new EntityNotFoundException(Flight.class);
        }
    }
}

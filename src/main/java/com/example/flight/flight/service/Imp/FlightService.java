package com.example.flight.flight.service.Imp;

import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import com.example.flight.base.util.ResponseHelper;
import com.example.flight.flight.entity.Flight;
import com.example.flight.flight.model.FlightRequestVO;
import com.example.flight.flight.repository.IFlightDao;
import com.example.flight.flight.service.IFlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService implements IFlightService {
    private final IFlightDao iFlightDao;
    public FlightService(IFlightDao iFlightDao){
        this.iFlightDao = iFlightDao;
    }
    @Override
    public ResponseEntity<BaseResponseVO> insertFlight(Flight flight) {
        List<Flight> flightList = iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(flight.getDestinationAirportCode(),flight.getSourceAirportCode(),flight.getAirlineCode());
        int flightCounter = checkFlightCountInADay(flight,flightList);
        if(flightCounter<3){
            flight = iFlightDao.save(flight);
            if(flight.getId()!=null){
                return ResponseHelper.getSuccessResponse(flight, ServiceMessage.INSERT_SUCCESS);

            }else{
                return ResponseHelper.getSuccessResponse("??",ServiceMessage.INSERT_FAILED);
            }

        }else{
            return ResponseHelper.getSuccessResponse("There must be daily at most 3 ﬂights for an airline between 2 destinations.",ServiceMessage.INSERT_FAILED);
        }

    }
    private int checkFlightCountInADay(Flight flight,List<Flight> flightList){
        int flightCounter=0;
        for(int i =0;i<flightList.size();i++){
            if(flightList.get(i).getFlightDate().compareTo(flight.getFlightDate())==0){
                flightCounter++;
            }
        }
        return flightCounter;
    }

    @Override
    public ResponseEntity<BaseResponseVO> updateFlight(FlightRequestVO flightRequestVO) {
        Optional<Flight> flightOptional = iFlightDao.findById(flightRequestVO.getId());
        Flight flight = flightOptional.isPresent() == true ? flightOptional.get() :null;
        if(flight !=null){
            FlightServiceUtil.changeFlightBasedOnFlightRequestVO(flightRequestVO,flight);
        }else{
            return ResponseHelper.getSuccessResponse("??",ServiceMessage.UPDATE_FAILED);
        }
        List<Flight> flightList = iFlightDao.customFindAllByDestCodeAndSourceCodeAndAirlineCode(flight.getDestinationAirportCode(),flight.getSourceAirportCode(),flight.getAirlineCode());
        int flightCounter = checkFlightCountInADay(flight,flightList);
        if(flightCounter<3){
            Flight savedflight = iFlightDao.save(flight);
            if(savedflight.getId()!=null){
                return ResponseHelper.getSuccessResponse(flight, ServiceMessage.INSERT_SUCCESS);

            }else{
                return ResponseHelper.getSuccessResponse("??",ServiceMessage.INSERT_FAILED);
            }

        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> deleteFlight(Long flightId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightById(Long flightId) {
        Optional<Flight> flightOptional = iFlightDao.findById(flightId);
        Flight flight = flightOptional.isPresent() == true ? flightOptional.get() :null;
        if(flight !=null){
            return ResponseHelper.getSuccessResponse(flight, ServiceMessage.GET_SUCCESS);
        }else{
            return ResponseHelper.getSuccessResponse("flight not found",ServiceMessage.GET_FAILED);
        }
    }

    @Override
    public ResponseEntity<BaseResponseVO> getFlightList() {
        ArrayList<Flight> airlineArrayList = iFlightDao.findAll();
        if(!airlineArrayList.isEmpty()){
            return ResponseHelper.getSuccessResponse(airlineArrayList, ServiceMessage.GET_SUCCESS);
        }else{
            return ResponseHelper.getSuccessResponse(ServiceMessage.GET_FAILED);
        }
    }
}

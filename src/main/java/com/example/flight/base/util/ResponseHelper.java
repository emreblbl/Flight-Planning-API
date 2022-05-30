package com.example.flight.base.util;

import com.example.flight.base.constant.ServiceCodeConstant;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class ResponseHelper {
    public static ResponseEntity<BaseResponseVO> getSuccessResponse(Object object, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(ServiceCodeConstant.SUCCESS_RESULT_CODE, null, object, serviceMessage), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponseVO> getSuccessResponse(ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(ServiceCodeConstant.SUCCESS_RESULT_CODE, null, serviceMessage), HttpStatus.OK);
    }


    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(ServiceCodeConstant.SUCCESS_RESULT_CODE, message, serviceMessage), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message, Object object, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(ServiceCodeConstant.SUCCESS_RESULT_CODE, message, object, serviceMessage), HttpStatus.OK);
    }




}

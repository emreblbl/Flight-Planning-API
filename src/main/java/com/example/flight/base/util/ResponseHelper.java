package com.example.flight.base.util;

import com.example.flight.base.exception.ApiError;
import com.example.flight.base.model.BaseResponseVO;
import com.example.flight.base.model.ServiceMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {
    public static ResponseEntity<BaseResponseVO> getSuccessResponse(Object object, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE, null, object, serviceMessage), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponseVO> getSuccessResponse(ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE, null, serviceMessage), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponseVO> getFailureResponse(String message, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(Constant.FAIL_RESULT_CODE, message, serviceMessage), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE, message, serviceMessage), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message, Object object, ServiceMessage serviceMessage) {
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE, message, object, serviceMessage), HttpStatus.OK);
    }
    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message, Object object, List<Error> errors, ServiceMessage serviceMessage){
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE,message,object,errors,serviceMessage),HttpStatus.OK);
    }
    public static ResponseEntity<BaseResponseVO> getSuccessResponse(String message,List<Error> errors,ServiceMessage serviceMessage){
        return new ResponseEntity(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE,message,errors,serviceMessage),HttpStatus.OK);
    }
    public static ResponseEntity<Object> getSuccessResponse(ApiError apiError,HttpStatus httpStatus){
        return new ResponseEntity<>(new BaseResponseVO(Constant.SUCCESS_RESULT_CODE,apiError),httpStatus);
    }

}

package com.example.flight.base.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponseVO {
    private int code;
    private String message;
    private Object result;
    private ServiceMessage message_code;
    public BaseResponseVO(int code, String message, ServiceMessage message_code) {
        this.code = code;
        this.message = message;
        this.message_code = message_code;
    }

    public BaseResponseVO(int code, String message, Object result, ServiceMessage message_code) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.message_code = message_code;
    }

}

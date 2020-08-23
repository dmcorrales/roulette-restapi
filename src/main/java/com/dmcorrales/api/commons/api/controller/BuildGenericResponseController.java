package com.dmcorrales.api.commons.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public abstract class BuildGenericResponseController<HV> {

    public ResponseEntity<RestResponse<Map>> buildListResponse(String value, HttpStatus status, Map obj, String errorMsg ){
        return new ResponseEntity<>(
                new RestResponse<Map>(status.value(), errorMsg, value , obj),
                HttpStatus.valueOf(status.name()));
    }

    public ResponseEntity<RestResponse<HV>> buildResponse(String value, HttpStatus status, HV obj , String errorMsg){
        return new ResponseEntity<>(
                new RestResponse<>(status.value(), errorMsg, value , obj),
                HttpStatus.valueOf(status.name()));
    }

    public ResponseEntity<RestResponse<HV>> buildResponse(String value, HttpStatus status){
        return buildResponse(value, status, null, null);
    }

    public ResponseEntity<RestResponse<HV>> buildErrorResponse(String value, HttpStatus status){
        return buildResponse(null, status, null, value);
    }
}

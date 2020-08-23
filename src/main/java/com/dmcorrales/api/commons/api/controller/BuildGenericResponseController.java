package com.dmcorrales.api.commons.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public abstract class BuildGenericResponseController<HV> {

    public ResponseEntity<RestResponse<Object>> buildGenericResponse(String value, HttpStatus status, Object obj ){
        return new ResponseEntity<>(
                new RestResponse<Object>(status.value(), true, null, value , obj),
                HttpStatus.OK);
    }

    public ResponseEntity<RestResponse<Map>> buildListResponse(String value, HttpStatus status, Map obj ){
        return new ResponseEntity<>(
                new RestResponse<Map>(status.value(), true, null, value , obj),
                HttpStatus.OK);
    }

    public ResponseEntity<RestResponse<HV>> buildResponse(String value, HttpStatus status, HV obj ){
        return new ResponseEntity<>(
                new RestResponse<>(status.value(), true, null, value , obj),
                HttpStatus.OK);
    }

    public ResponseEntity<RestResponse<HV>> buildResponse(String value, HttpStatus status){
        return buildResponse(value, status, null);
    }
}

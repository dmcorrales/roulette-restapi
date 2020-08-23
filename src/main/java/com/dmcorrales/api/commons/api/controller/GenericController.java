package com.dmcorrales.api.commons.api.controller;

import com.dmcorrales.api.commons.api.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class GenericController<HK, HV>  extends BuildGenericResponseController<HV>{

    protected abstract GenericService<HK, HV> getService();

    @PostMapping
    @ResponseBody
    ResponseEntity<RestResponse<HV>> create(@RequestBody(required = false) HV input, HttpServletResponse response){
        String id;
        try {
            id = getService().save(input);
        } catch (Exception e) {
            return buildErrorResponse("¡Vaya! no se ha podido insertar la entidad",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    return buildResponse(id, HttpStatus.CREATED);
    }

    @GetMapping
    List<HV> list(){
        return getService().findAll();
    }
}

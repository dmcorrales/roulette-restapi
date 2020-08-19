package com.dmcorrales.api.commons.api.controller;

import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<HK, HV>  extends BuildGenericResponseController<HV>{

    protected abstract GenericService<HK, HV> getService();

    @PostMapping()
    @ResponseBody
    ResponseEntity<RestResponse<HV>> create(@RequestBody HV input){
        String id = getService().save(input);
        return buildResponse(id, HttpStatus.OK);
    }

    @GetMapping()
    List<HV> list(){
        return getService().findAll();
    }
}

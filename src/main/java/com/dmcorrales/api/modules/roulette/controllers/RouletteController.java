package com.dmcorrales.api.modules.roulette.controllers;

import com.dmcorrales.api.commons.api.controller.GenericController;
import com.dmcorrales.api.commons.api.controller.RestResponse;
import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.services.impl.RouletteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/roulette")
public class RouletteController extends GenericController<String, Roulette> {

    @Autowired
    RouletteServiceImpl service;

    @Override
    protected GenericService getService() {
        return service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity<RestResponse<Roulette>> opening(@PathVariable("id") String id){
        Roulette entity = null;
        try {
             entity = service.opening(id);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return buildResponse("Se ha realizado correctamente la apertura de la ruleta", HttpStatus.OK, entity);
    }
}

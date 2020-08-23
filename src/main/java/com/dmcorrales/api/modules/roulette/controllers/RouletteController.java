package com.dmcorrales.api.modules.roulette.controllers;

import com.dmcorrales.api.commons.api.controller.GenericController;
import com.dmcorrales.api.commons.api.controller.RestResponse;
import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.roulette.dto.BetInput;
import com.dmcorrales.api.modules.roulette.dto.BetOutput;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.services.impl.RouletteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/roulette")
public class RouletteController extends GenericController<String, Roulette> {

    @Autowired
    RouletteServiceImpl service;

    @Override
    protected GenericService<String, Roulette> getService() {
        return service;
    }

    @GetMapping("opening/{id}")
    @ResponseBody
    ResponseEntity<RestResponse<Roulette>> opening(@PathVariable("id") String id, HttpServletResponse response){
        Roulette entity = null;
        try {
             entity = service.opening(id);
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return buildResponse("Se ha realizado correctamente la apertura de la ruleta", HttpStatus.OK, entity,
                null);
    }

    @GetMapping("closing/{id}")
    @ResponseBody
    ResponseEntity<RestResponse<Map>> closing(@PathVariable("id") String id, HttpServletResponse response){
        Map<String, BetOutput> entity = null;
        try {
            entity = service.closing(id);
        } catch (Exception e) {
            return buildListResponse(null, HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
        return buildListResponse("Se ha realizado la apuesta", HttpStatus.OK, entity, null);
    }

    @GetMapping({"/bet/{id}"})
    @ResponseBody
    ResponseEntity<RestResponse<Roulette>> bet(@PathVariable(value = "id", required = true) String id,
                                               @RequestBody BetInput betInput,
                                               @RequestHeader(value = "user-id", required = true) String userId,
                                               HttpServletResponse response){
        Roulette entity;
        try {
            entity = service.bet(id, betInput, userId);
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return buildResponse("Se ha realizado la apuesta", HttpStatus.OK, entity, null);
    }

}

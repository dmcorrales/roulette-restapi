package com.dmcorrales.api.modules.roulette.controllers;

import com.dmcorrales.api.commons.api.controller.GenericController;
import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.services.impl.RouletteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roulette")
public class RouletteController extends GenericController<String, Roulette> {

    @Autowired
    RouletteServiceImpl service;

    @Override
    protected GenericService getService() {
        return service;
    }
}

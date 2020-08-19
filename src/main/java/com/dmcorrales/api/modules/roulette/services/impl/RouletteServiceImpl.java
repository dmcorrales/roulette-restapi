package com.dmcorrales.api.modules.roulette.services.impl;

import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.repository.RouletteRepository;
import com.dmcorrales.api.modules.roulette.services.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouletteServiceImpl extends GenericService<String, Roulette> implements RouletteService {

    @Autowired
    RouletteRepository repository;

    public static final String HEADER = "ROULETTE";

    @Override
    public String save(Roulette entity) {
        return repository.save(entity);
    }

    @Override
    public List<Roulette> findAll() {
        return repository.findAll();
    }

    public Roulette opening(String key) throws Exception {
        Roulette roulette = repository.findById(key);
        if(key == null)
            throw new Exception("La llave está vacía");
        if(roulette == null)
            throw new Exception("No existe la ruleta");
        roulette.setStatus(Boolean.TRUE);
        repository.updateStatus(roulette);
        return roulette;
    }
}

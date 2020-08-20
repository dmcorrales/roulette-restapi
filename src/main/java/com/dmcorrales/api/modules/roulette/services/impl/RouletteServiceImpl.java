package com.dmcorrales.api.modules.roulette.services.impl;

import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.commons.helpers.RandomValueGenerator;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.entities.Score;
import com.dmcorrales.api.modules.roulette.enums.TypeEnum;
import com.dmcorrales.api.modules.roulette.repository.RouletteRepository;
import com.dmcorrales.api.modules.roulette.services.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class RouletteServiceImpl extends GenericService<String, Roulette> implements RouletteService {

    public static final String ROJO = "rojo";
    public static final String NEGRO = "negro";

    @Autowired
    RouletteRepository repository;

    @Override
    public String save(Roulette entity) {
        entity.setId(UUID.randomUUID().toString().replace("-", ""));
        return repository.save(entity);
    }

    @Override
    public List<Roulette> findAll() {
        return repository.findAll();
    }

    public Roulette bet(String key, Integer number, String color, TypeEnum type, Double money) throws Exception {
        Roulette roulette = repository.findById(key);
        if(roulette == null)
            throw new Exception("No se ha encontrado la ruleta");
        if(money == null || !(money >= 1 && money <= 10000))
            throw new Exception("El rango en dinero para apuesta no está en el rango (entre 0 y 10000)");
        if(!validateValueByType(number,color, type))
            throw new Exception("Los valores ingresados no son válidos");
        if(roulette.getStatus() == Boolean.FALSE)
            throw new Exception("La ruleta está cerrada");
        if(key == null)
            throw new Exception("La llave está vacía");
        validateScoreArray(roulette, buildRouletteBet(number,color,money,type));
        repository.save(roulette);
        return roulette;
    }

    private Score buildRouletteBet(Integer number, String color, Double money, TypeEnum typeEnum){
        String resultValue = typeEnum.equals(TypeEnum.NUMBER) ?
                String.valueOf(RandomValueGenerator.generateRandomNumber()) :
                RandomValueGenerator.generateRandomColor().getValue();
        String value = typeEnum.equals(TypeEnum.NUMBER) ?
                String.valueOf(number) :
                color;
        if(value.equalsIgnoreCase(resultValue)){
            System.out.println("Value="+value);
            System.out.println("ResultValue="+resultValue);
        }
        return new Score(value, resultValue, money);
    }

    private boolean validateValueByType(Integer number, String color, TypeEnum typeEnum){
        if(typeEnum.equals(TypeEnum.NUMBER)){
            return number >= 0 && number <= 36;
        }
        return color.equals(ROJO) || color.equals(NEGRO);
    }

    private void validateScoreArray(Roulette roulette, Score score) {
        if(roulette.getScore().isEmpty() || roulette.getScore() == null){
            LinkedList list = new LinkedList<>();
            list.add(score);
            roulette.setScore(list);
        }else
            roulette.getScore().add(score);
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

    public Roulette closing(String key) throws Exception {
        Roulette roulette = repository.findById(key);
        if(key == null)
            throw new Exception("La llave está vacía");
        if(roulette == null)
            throw new Exception("No existe la ruleta");
        roulette.setStatus(Boolean.FALSE);
        repository.updateStatus(roulette);
        return roulette;
    }
}

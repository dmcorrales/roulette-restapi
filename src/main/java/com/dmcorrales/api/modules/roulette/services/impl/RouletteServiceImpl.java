package com.dmcorrales.api.modules.roulette.services.impl;

import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.commons.helpers.RandomValueGenerator;
import com.dmcorrales.api.modules.roulette.dto.BetInput;
import com.dmcorrales.api.modules.roulette.dto.BetOutput;
import com.dmcorrales.api.modules.roulette.entities.Bet;
import com.dmcorrales.api.modules.roulette.entities.Roulette;
import com.dmcorrales.api.modules.roulette.enums.ColorEnum;
import com.dmcorrales.api.modules.roulette.enums.TypeEnum;
import com.dmcorrales.api.modules.roulette.repository.RouletteRepository;
import com.dmcorrales.api.modules.roulette.services.RouletteService;
import com.dmcorrales.api.modules.user.entities.User;
import com.dmcorrales.api.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RouletteServiceImpl extends GenericService<String, Roulette> implements RouletteService {

    public static final String ROJO = "rojo";
    public static final String NEGRO = "negro";

    @Autowired
    RouletteRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String save(Roulette entity) {
        if(entity == null){
            entity = new Roulette();
            entity.setStatus(Boolean.FALSE);
        }
        entity.setId(UUID.randomUUID().toString().replace("-", ""));
        return repository.save(entity);
    }

    @Override
    public List<Roulette> findAll() {
        return repository.findAll();
    }

    public Roulette bet(String key, BetInput dto, String userId) throws Exception {
        User user = userRepository.findById(userId);
        TypeEnum type = this.isValidType(dto);
        Roulette roulette = repository.findById(key);
        if(user == null)
            throw new Exception("No se ha encontrado el usuario");
        if(roulette == null)
            throw new Exception("No se ha encontrado la ruleta");
        if(roulette.getStatus().equals(Boolean.FALSE))
            throw new Exception("La ruleta está cerrada");
        if(user.getBalance() < dto.getMoney())
            throw new Exception("El usuario no posee fondo suficiente para apostar");
        if(!(dto.getMoney() >= 1 && dto.getMoney() <= 10000))
            throw new Exception("El rango en dinero para apuesta no está en el rango (entre 0 y 10000)");
        if(!validateValueByType(dto.getNumber(),dto.getColor(), type))
            throw new Exception("Los valores ingresados no son válidos");
        if(key == null)
            throw new Exception("La llave está vacía");
        validateScoreArray(roulette, buildRouletteBet(dto.getNumber(),dto.getColor(),dto.getMoney(),type,user));
        repository.save(roulette);
        return roulette;
    }

    private TypeEnum isValidType(BetInput betInput) throws Exception {
        if(StringUtils.isEmpty(String.valueOf(betInput.getNumber())) && StringUtils.isEmpty(betInput.getColor()))
            throw new Exception("No se ha ingresado el TIPO esperado {color} o {number}");
        else if(betInput.getNumber() > 0 && !StringUtils.isEmpty(betInput.getColor()))
            throw new Exception("Se espera solo un TIPO {color} o {number}");
        else if(!StringUtils.isEmpty(betInput.getColor()))
            return TypeEnum.COLOR;
        else if(betInput.getNumber() > 0)
            return TypeEnum.NUMBER;
        throw new Exception("No se ha ingresado un tipo de dato esperado.");
    }

    private Bet buildRouletteBet(Integer number, String color, Double money, TypeEnum typeEnum, User user){
        String value = typeEnum.equals(TypeEnum.NUMBER) ?
                String.valueOf(number) :
                color;
        return new Bet(value, money, user);
    }

    private boolean validateValueByType(Integer number, String color, TypeEnum typeEnum){
        if(typeEnum.equals(TypeEnum.NUMBER)){
            return number >= 0 && number <= 36;
        }
        return color.equals(ROJO) || color.equals(NEGRO);
    }

    private void validateScoreArray(Roulette roulette, Bet bet) throws Exception {
        if(roulette.getBet().isEmpty() || roulette.getBet() == null){
            LinkedList<Bet> list = new LinkedList<>();
            list.add(bet);
            roulette.setBet(list);
        }else{
            boolean isPresent = roulette.getBet().stream().anyMatch((b) ->
                    b.getUser().getId().equals(bet.getUser().getId()));
            if(isPresent)
                throw  new Exception("Vaya! Solo puedes apostar una vez");
            else
                roulette.getBet().add(bet);
        }
    }

    public Roulette opening(String key) throws Exception {
        Roulette roulette = repository.findById(key);
        if(key == null)
            throw new Exception("La llave está vacía");
        if(roulette == null)
            throw new Exception("No existe la ruleta");
        if(roulette.getStatus().equals(Boolean.TRUE))
            throw  new Exception("La ruleta ya se encontraba activa");
        if(!roulette.getBet().isEmpty())
            roulette.getBet().clear();
        roulette.setStatus(Boolean.TRUE);
        repository.updateStatus(roulette);
        return roulette;
    }

    public Map<String, BetOutput> closing(String key) throws Exception {
        Roulette roulette = repository.findById(key);
        if(key == null)
            throw new Exception("La llave está vacía");
        if(roulette == null)
            throw new Exception("No existe la ruleta");
        if(roulette.getStatus().equals(Boolean.FALSE))
            throw new Exception("La ruleta se encontraba cerrada");
        roulette.setStatus(Boolean.FALSE);
        Integer resultNumber = RandomValueGenerator.generateRandomNumber();
        repository.updateStatus(roulette);
        return buildClose(roulette, resultNumber);
    }

    public Map<String, BetOutput> buildClose(Roulette roulette , Integer resultNumber){
        List<Bet> winners = new ArrayList<>();
        List<Bet> losers = new ArrayList<>();
        ColorEnum colorEnum = ColorEnum.NEGRO;
        if((resultNumber % 2) == 0) colorEnum = ColorEnum.ROJO;
        for (Bet bet : roulette.getBet())
            buildListResults(resultNumber, winners, losers, colorEnum, bet);
        Map<String, BetOutput> result = new LinkedHashMap<>();
        result.put("result", new BetOutput(resultNumber, colorEnum.getValue(),
                winners.size(), losers.size(), winners,losers));
        return result;
    }

    private void buildListResults(Integer resultNumber, List<Bet> winners, List<Bet> losers,
                                 ColorEnum colorEnum, Bet bet) {
        if(bet.getValue().equals(colorEnum.getValue()) || bet.getValue().equals(String.valueOf(resultNumber))){
            bet.getUser().setBalance(bet.getUser().getBalance() + bet.getCash());
            winners.add(bet);
        }else{
            bet.getUser().setBalance(bet.getUser().getBalance() - bet.getCash());
            losers.add(bet);
        }
        userRepository.save(bet.getUser());
    }
}

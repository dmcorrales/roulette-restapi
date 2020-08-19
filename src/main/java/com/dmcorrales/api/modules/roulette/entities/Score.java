package com.dmcorrales.api.modules.roulette.entities;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("scores")
public class Score implements Serializable {
    private String value;
    private Double cash;
    @Reference
    private Roulette rouletteId;


    public Score(String value, Double cash, Roulette rouletteId) {
        this.value = value;
        this.cash = cash;
        this.rouletteId = rouletteId;
    }
}

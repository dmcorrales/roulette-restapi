package com.dmcorrales.api.modules.roulette.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RedisHash("Roulette")
public class Roulette implements Serializable {
    @Id
    private String id;
    private Integer number;
    private Boolean status;
    private List<Score> score;

    public Roulette(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }
}


package com.dmcorrales.api.modules.roulette.entities;
import com.dmcorrales.api.modules.user.entities.User;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("scores")
public class Bet implements Serializable {

    private static final long serialVersionUID = 7821110451301315929L;

    private String value;
    private Double cash;
    private User user;

    public Bet(String value, Double cash, User user) {
        this.value = value;this.cash = cash;
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

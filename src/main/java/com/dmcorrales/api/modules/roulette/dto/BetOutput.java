package com.dmcorrales.api.modules.roulette.dto;
import java.io.Serializable;
import java.util.Map;

public class BetOutput implements Serializable {

    private int status;
    private String message;
    private int totalWinners;
    private int totalLosers;
    private Map list;

    public BetOutput(int status, String message, int totalWinners, int totalLosers, Map list) {
        this.message = message;
        this.totalWinners = totalWinners;
        this.totalLosers = totalLosers;
        this.list = list;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalWinners() {
        return totalWinners;
    }

    public void setTotalWinners(int totalWinners) {
        this.totalWinners = totalWinners;
    }

    public int getTotalLosers() {
        return totalLosers;
    }

    public void setTotalLosers(int totalLosers) {
        this.totalLosers = totalLosers;
    }

    public Map getList() {
        return list;
    }

    public void setList(Map list) {
        this.list = list;
    }
}


package com.dmcorrales.api.modules.roulette.dto;

import com.dmcorrales.api.modules.roulette.entities.Bet;

import java.io.Serializable;
import java.util.List;

public class BetOutput implements Serializable {

    private int result_number;
    private String result_color;
    private int totalWinners;
    private int totalLosers;
    private List<Bet> listWinners;
    private List<Bet>listLosers;


    public BetOutput(int result_number, String result_color, int totalWinners, int totalLosers, List<Bet> listWinners, List<Bet> listLosers) {
        this.result_number = result_number;
        this.result_color = result_color;
        this.totalWinners = totalWinners;
        this.totalLosers = totalLosers;
        this.listWinners = listWinners;
        this.listLosers = listLosers;
    }

    public int getResult_number() {
        return result_number;
    }

    public void setResult_number(int result_number) {
        this.result_number = result_number;
    }

    public String getResult_color() {
        return result_color;
    }

    public void setResult_color(String result_color) {
        this.result_color = result_color;
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

    public List<Bet> getListWinners() {
        return listWinners;
    }

    public void setListWinners(List<Bet> listWinners) {
        this.listWinners = listWinners;
    }

    public List<Bet> getListLosers() {
        return listLosers;
    }

    public void setListLosers(List<Bet> listLosers) {
        this.listLosers = listLosers;
    }
}


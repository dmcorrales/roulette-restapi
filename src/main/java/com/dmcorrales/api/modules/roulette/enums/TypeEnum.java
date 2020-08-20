package com.dmcorrales.api.modules.roulette.enums;

public enum TypeEnum {
    NUMBER("number"),
    COLOR("color");

    private String value;
    TypeEnum(String s){
        value = s;
    }

    public String getValue(){
        return value;
    }
}

package com.dmcorrales.api.commons.helpers;

import com.dmcorrales.api.modules.roulette.enums.ColorEnum;


public class RandomValueGenerator {

    public RandomValueGenerator() {
    }

    public static ColorEnum generateRandomColor(){
        ColorEnum[] listColorEnum = ColorEnum.values();
        int max = (listColorEnum.length-1);
        int min = 0;
        int i = (int) ((Math.random() * ((max - min) + 1)) + min);
        return listColorEnum[i];
    }

    public static int generateRandomNumber(){
        int max = 36;
        int min = 0;
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

}

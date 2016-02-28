package com.efelnic.factapp;

import java.util.Random;

/**
 * Created by efelnic on 2/1/16.
 */
public class FactsModel {


    public String getFacts(String[] facts){
        Random randomGenerator;
        randomGenerator = new Random();
        return facts[ ( randomGenerator.nextInt(facts.length))];
    }
}

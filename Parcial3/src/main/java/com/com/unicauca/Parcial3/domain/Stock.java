package com.com.unicauca.Parcial3.domain;

import lombok.Data;

@Data
public class Stock {
    private int id;
    private String name;
    private double currentPrice;
    private double previousPrice;
    private double lowerThreshold;
    private double upperThreshold;

    public Stock() {

    }

    public Stock(int id, String name, double currentPrice, double previousPrice, double lowerThreshold, double upperThreshold) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.previousPrice = previousPrice;
        this.lowerThreshold = lowerThreshold;
        this.upperThreshold = upperThreshold;
    }

}
package com.com.unicauca.Parcial3.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private int id;
    private ArrayList<Stock> stocks;

    public User(int id) {
        this.id = id;
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    /**
     * @param id Stock id to search
     * @return Stock if found, null if not found
     */
    public Stock getStockById(int id) {
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }

    /**
     * @param id Stock id to update
     * @param upperThreshold New upper threshold
     * @return true if stock was found and updated, false if not found
     */
    public boolean updateUpperThreshold(int id, double upperThreshold) {
        Stock stock = getStockById(id);
        if (stock != null) {
            stock.setUpperThreshold(upperThreshold);
            return true;
        }
        return false;
    }

    /**
     * @param id Stock id to update
     * @param lowerThreshold New upper threshold
     * @return true if stock was found and updated, false if not found
     */
    public boolean updateLowerThreshold(int id, double lowerThreshold) {
        Stock stock = getStockById(id);
        if (stock != null) {
            stock.setLowerThreshold(lowerThreshold);
            return true;
        }
        return false;
    }
}

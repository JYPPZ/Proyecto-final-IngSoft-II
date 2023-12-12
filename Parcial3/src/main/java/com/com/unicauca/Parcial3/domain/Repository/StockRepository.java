package com.com.unicauca.Parcial3.domain.Repository;

import com.com.unicauca.Parcial3.domain.Stock;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

@Data
@Repository
public class StockRepository implements IStockRepository {
    private ArrayList<Stock> stocks;

    public StockRepository() {
        stocks = new ArrayList<Stock>();
        initDB();
    }

    /**
     * Initialize the database with some stocks
     */
    public void initDB() {
        stocks.add(new Stock(1000, "GOOG", 1000, 1000, 900, 1100));
        stocks.add(new Stock(1001, "AAPL", 200, 200, 180, 220));
        stocks.add(new Stock(1002, "MSFT", 300, 300, 270, 330));
        stocks.add(new Stock(1003, "AMZN", 400, 400, 360, 440));
        stocks.add(new Stock(1004, "FB", 500, 500, 450, 550));
    }

    @Override
    public List<Stock> findAll() {
        return stocks;
    }

    @Override
    public Stock findById(int id) {
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }

    @Override
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }
}

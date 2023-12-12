package com.com.unicauca.Parcial3.aplication;

import com.com.unicauca.Parcial3.domain.Stock;
import com.com.unicauca.Parcial3.domain.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

@RestController
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public List<Stock> findAll() {
        return stockService.findAll();
    }
}

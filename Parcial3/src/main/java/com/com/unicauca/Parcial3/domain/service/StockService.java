package com.com.unicauca.Parcial3.domain.service;

import com.com.unicauca.Parcial3.domain.Repository.IStockRepository;
import com.com.unicauca.Parcial3.domain.Stock;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class StockService {
    @Autowired
    private IStockRepository stockRepository;

    /**
     * @return List of all stocks
     */
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    /**
     * @param id Stock id to search
     * @return Stock if found, null if not found
     */
    public Stock findById(int id) {
        return stockRepository.findById(id);
    }
}

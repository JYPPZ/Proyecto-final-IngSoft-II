package com.com.unicauca.Parcial3.domain.Repository;

import com.com.unicauca.Parcial3.domain.Stock;

import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

public interface IStockRepository {
    /**
     * @return All stocks
     */
    List<Stock> findAll();

    /**
     * @param id Stock id to search
     * @return Stock if found, null if not found
     */
    Stock findById(int id);

    /**
     * @param stock Stock to add
     */
    void addStock(Stock stock);

    /**
     * @param stock Stock to remove
     */
    void removeStock(Stock stock);
}

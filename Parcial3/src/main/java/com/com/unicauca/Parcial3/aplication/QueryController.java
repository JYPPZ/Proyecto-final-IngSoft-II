package com.com.unicauca.Parcial3.aplication;

import com.com.unicauca.Parcial3.domain.Stock;
import com.com.unicauca.Parcial3.domain.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

@RestController
@RequestMapping("/query")
public class QueryController {

    private final QueryService queryService;
    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/stocks/{idUser}")
    public List<Stock> getStocks(@PathVariable int idUser) {
        return queryService.getStocks(idUser);
    }

    @GetMapping("/stock/{idUser}/{idStock}")
    public Stock getStockById(@PathVariable int idUser,@PathVariable int idStock) {
        return queryService.getStock(idUser, idStock);
    }


    @PostMapping("/addStock/{idUser}")
    public void addStock(@PathVariable int idUser,@RequestBody Stock stock) {
        queryService.addStock(idUser,stock);
        //queryService.getStockRepository().addStock(stock);
    }

    @DeleteMapping("/removeStock/{idUser}/{idStock}")
    public void removeStock(@PathVariable int idUser,@PathVariable int idStock) {
        queryService.removeStock(idUser,idStock);
        queryService.getStockRepository().removeStock(queryService.getStockRepository().findById(idStock));
    }

    @PatchMapping("/newUppLim/{idUser}/{idStock}/{upperThreshold}")
    public void updateUpperThreshold(@PathVariable int idUser,@PathVariable int idStock,@PathVariable double upperThreshold) {
        queryService.updateUpperThreshold(idUser,idStock,upperThreshold);
    }

    @PatchMapping("/newLowLim/{idUser}/{idStock}/{lowerThreshold}")
    public void updateLowerThreshold(@PathVariable int idUser,@PathVariable int idStock,@PathVariable double lowerThreshold) {
        queryService.updateLowerThreshold(idUser,idStock,lowerThreshold);
    }
}
package com.com.unicauca.Parcial3.domain.service;

import com.com.unicauca.Parcial3.infrastructure.RabbitMqConfig;
import com.com.unicauca.Parcial3.domain.Stock;
import com.com.unicauca.Parcial3.domain.User;
import com.com.unicauca.Parcial3.domain.Repository.IStockRepository;
import com.com.unicauca.Parcial3.domain.Repository.IUserRepository;
import com.com.unicauca.Parcial3.domain.Repository.StockRepository;
import com.com.unicauca.Parcial3.domain.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class QueryService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        System.out.println("QueryService inicializado con éxito. AmqpTemplate: " + rabbitTemplate);
    }

    public QueryService() {
        userRepository = new UserRepository();
        stockRepository = new StockRepository();
    }

    /**
     * @return List of all users
     */
    public List<Stock> getStocks(int idUser) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            return userAux.getStocks();
        }
        return null;
    }

    /**
     * @param idUser  User id to search
     * @param idStock Stock id to search
     * @return Stock if found, null if not found
     */
    public Stock getStock(int idUser, int idStock) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            return userAux.getStockById(idStock);
        }
        return null;
    }

    /**
     * @param idUser         User id to search
     * @param idStock        Stock id to update
     * @param upperThreshold New upper threshold
     * @return true if stock was found and updated, false if not found
     */
    public boolean updateUpperThreshold(int idUser, int idStock, double upperThreshold) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            boolean updated = userAux.updateUpperThreshold(idStock, upperThreshold);
            if (updated) {
                stockRepository.findById(idStock).setUpperThreshold(upperThreshold);
                sendMessage("Se ha actualizado el umbral superior para la acción " + idStock);
            }
            return updated;
        }
        return false;
    }

    /**
     * @param idUser         User id to search
     * @param idStock        Stock id to update
     * @param lowerThreshold New upper threshold
     * @return true if stock was found and updated, false if not found
     */
    public boolean updateLowerThreshold(int idUser, int idStock, double lowerThreshold) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            boolean updated = userAux.updateLowerThreshold(idStock, lowerThreshold);
            if (updated) {
                stockRepository.findById(idStock).setLowerThreshold(lowerThreshold);
                sendMessage("Se ha actualizado el umbral inferior para la acción " + idStock);
            }
            return updated;
        }
        return false;
    }

    /**
     * @param idUser User id to search
     * @param stock  Stock id to search
     * @return true if stock was found and deleted, false if not found
     */
    public boolean addStock(int idUser, Stock stock) {
        User userAux = userRepository.findById(idUser);
        Stock stockAux = stockRepository.findById(stock.getId());
        if (userAux != null) {
            if (stockAux != null) {
                userRepository.findById(idUser).addStock(stockAux);
            } else {
                userRepository.findById(idUser).addStock(stock);
                stockRepository.addStock(stock);
            }
            return true;
        }
        return false;
    }

    /**
     * @param idUser  User id to search
     * @param idStock Stock id to search
     * @return true if stock was found and deleted, false if not found
     */
    public boolean removeStock(int idUser, int idStock) {
        User userAux = userRepository.findById(idUser);
        Stock stockAux = stockRepository.findById(idStock);
        if (userAux != null && stockAux != null) {
            userAux.removeStock(stockAux);
            return true;
        }
        return false;
    }

    /**
     * Método para enviar un mensaje a RabbitMQ.
     */
    private void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, message);
        System.out.println("Mensaje enviado a RabbitMQ: " + message);
    }
}

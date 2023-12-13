package com.com.unicauca.Parcial3.domain.service;

import com.com.unicauca.Parcial3.domain.Repository.IStockRepository;
import com.com.unicauca.Parcial3.domain.Repository.IUserRepository;
import com.com.unicauca.Parcial3.domain.Repository.StockRepository;
import com.com.unicauca.Parcial3.domain.Repository.UserRepository;
import com.com.unicauca.Parcial3.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.com.unicauca.Parcial3.domain.Stock;
import java.util.List;

@Data
@Service
public class QueryService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IStockRepository stockRepository;

    @PostConstruct
    public void init() {
        System.out.println("QueryService inicializado con éxito. AmqpTemplate: " + rabbitTemplate);
    }

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    public QueryService(RabbitTemplate rabbitTemplate){
        userRepository = new UserRepository();
        stockRepository = new StockRepository();
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @return List of all users
     */
    public List<Stock> getStocks(int idUser) {
        User userAux = userRepository.findById(idUser);
        if(userAux != null){
            return userAux.getStocks();
        }
        return null;
    }
    /**
     * @param idUser User id to search
     * @param idStock Stock id to search
     * @return Stock if found, null if not found
     */
    public Stock getStock(int idUser, int idStock) {
        User userAux = userRepository.findById(idUser);
        if(userAux != null){
            return userAux.getStockById(idStock);
        }
        return null;
    }

    /**
     * @param idUser         User id to search
     * @param idStock        Stock id to update
     * @param upperThreshold New upper threshold
     */
    public void updateUpperThreshold(int idUser, int idStock, double upperThreshold) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            boolean updated = userAux.updateUpperThreshold(idStock, upperThreshold);
            if (updated) {
                stockRepository.findById(idStock).setUpperThreshold(upperThreshold);
                sendMessage("Se ha actualizado el umbral superior para la acción " + idStock);
            }
        }
    }

    /**
     * @param idUser         User id to search
     * @param idStock        Stock id to update
     * @param lowerThreshold New upper threshold
     */
    public void updateLowerThreshold(int idUser, int idStock, double lowerThreshold) {
        User userAux = userRepository.findById(idUser);
        if (userAux != null) {
            boolean updated = userAux.updateLowerThreshold(idStock, lowerThreshold);
            if (updated) {
                stockRepository.findById(idStock).setLowerThreshold(lowerThreshold);
                sendMessage("Se ha actualizado el umbral inferior para la acción " + idStock);
            }
        }
    }

    /**
     * @param idUser User id to search
     * @param stock  Stock id to search
     */
    public void addStock(int idUser, Stock stock) {
        User userAux = userRepository.findById(idUser);
        Stock stockAux = stockRepository.findById(stock.getId());
        if(userAux != null){
            if(stockAux != null){
                userRepository.findById(idUser).addStock(stockAux);
            }else{
                userRepository.findById(idUser).addStock(stock);
                stockRepository.addStock(stock);
            }
        }
    }

    /**
     * @param idUser  User id to search
     * @param idStock Stock id to search
     */
    public void removeStock(int idUser, int idStock) {
        User userAux = userRepository.findById(idUser);
        Stock stockAux = stockRepository.findById(idStock);
        if(userAux != null && stockAux != null){
            userAux.removeStock(stockAux);
        }
    }

    /**
     * Método para enviar un mensaje a RabbitMQ.
     */
    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}

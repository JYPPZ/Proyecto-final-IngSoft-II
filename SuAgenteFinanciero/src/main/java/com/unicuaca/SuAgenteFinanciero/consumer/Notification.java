package com.unicuaca.SuAgenteFinanciero.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Notification {
    private static final Logger LOGGER = LoggerFactory.getLogger(Notification.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumerNotification(@Payload String message) {
        LOGGER.info(String.format("Mensaje recibido: %s", message));
    }
}

package com.neyra.arqui.msproductos.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neyra.arqui.msproductos.model.EmailNotification;

@Service
public class MessagePublisher {
  private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

  private final RabbitTemplate rabbitTemplate;
  private boolean rabbitMQAvailable = true;

  @Value("${app.rabbitmq.exchange}")
  private String exchange;

  @Value("${app.rabbitmq.routingkey}")
  private String routingKey;

  public MessagePublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;

    try {
      rabbitTemplate.execute(channel -> null);
      logger.info("RabbitMQ connection successful");
    } catch (AmqpConnectException e) {
      logger.warn("RabbitMQ connection failed: {}. Notifications will be logged only.", e.getMessage());
      rabbitMQAvailable = false;
    }
  }

  public void publishNewProductNotification(EmailNotification notification) {
    try {
      logger.info("Sending order notification to queue: {}", notification.getTo());
      rabbitTemplate.convertAndSend(exchange, routingKey, notification);
      logger.info("Order notification sent successfully to queue");
    } catch (Exception e) {
      logger.error("Error sending order notification: {}", e.getMessage(), e);
      logger.info("Order would have been sent to: {}", notification.getTo());
      logger.info("Order Subject: {}", notification.getSubject());
      logger.info("Order Body: {}", notification.getBody());
    }
  }
}

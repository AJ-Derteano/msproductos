package com.neyra.arqui.msproductos.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.neyra.arqui.msproductos.model.EmailNotification;
import com.neyra.arqui.msproductos.service.EmailService;

@Component
public class MessageConsumer {

  private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

  private final EmailService emailService;

  public MessageConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "${app.rabbitmq.queue}")
  public void receiveMessage(EmailNotification emailNotification) {
    logger.info("Received message from RabbitMQ: {}", emailNotification.getTo());
    try {
      emailService.sendEmail(emailNotification);
      logger.info("Email sent successfully to: {}", emailNotification.getTo());
    } catch (Exception e) {
      logger.error("Failed to send email: {}", e.getMessage(), e);
    }
    logger.info("Email Subject: {}", emailNotification.getSubject());
  }

}

package com.neyra.arqui.msproductos.service;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.neyra.arqui.msproductos.model.EmailNotification;

@Service
public class EmailService {
  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EmailService.class);

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendEmail(EmailNotification notification) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(notification.getTo());
      message.setSubject(notification.getSubject());
      message.setText(notification.getBody());

      mailSender.send(message);
      logger.info("Email sent to: {}", notification.getTo());
    } catch (Exception e) {
      logger.error("Failed to send email: {}", e.getMessage(), e);
      throw new RuntimeException("Failed to send email", e);
    }
  }

}

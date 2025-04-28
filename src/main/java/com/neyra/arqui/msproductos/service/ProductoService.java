package com.neyra.arqui.msproductos.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neyra.arqui.msproductos.dto.ProductoRequest;
import com.neyra.arqui.msproductos.messaging.MessagePublisher;
import com.neyra.arqui.msproductos.model.EmailNotification;
import com.neyra.arqui.msproductos.model.Producto;
import com.neyra.arqui.msproductos.repository.ProductoRepository;

@Service
public class ProductoService {

  private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

  private final ProductoRepository jpaRepository;
  private final MessagePublisher messagePublisher;

  public ProductoService(ProductoRepository jpaRepository, MessagePublisher messagePublisher) {
    this.jpaRepository = jpaRepository;
    this.messagePublisher = messagePublisher;
  }

  public Producto save(ProductoRequest producto) {

    // Email, este deberia salir del usuario logueado
    String email = "aneyra@test.com";

    Producto productoEntity = Producto.builder()
        .codigo(producto.getCodigo())
        .nombre(producto.getNombre())
        .descripcion(producto.getDescripcion())
        .precio(producto.getPrecio())
        .stock(producto.getStock())
        .build();

    Producto productoSaved = jpaRepository.save(productoEntity);

    logger.info("Producto creado: {}", productoEntity.getId());
    logger.info("Enviando notificacion a: {}", email);

    try {
      EmailNotification notification = EmailNotification.forNewProduct(productoEntity, email);
      messagePublisher.publishNewProductNotification(notification);
      logger.info("Notificacion enviada a: {}", email);
    } catch (Exception e) {
      logger.error("Error al enviar la notificacion: {}", e.getMessage(), e);
      logger.info("Notificacion no enviada a: {}", email);
    }

    return productoSaved;
  }

  public Page<Producto> findAll(Pageable pageable) {
    return jpaRepository.findAll(pageable);
  }

  public Optional<Producto> findById(Long id) {
    return jpaRepository.findById(id);

  }

}

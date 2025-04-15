package com.neyra.arqui.msproductos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neyra.arqui.msproductos.dto.ProductoRequest;
import com.neyra.arqui.msproductos.model.Producto;
import com.neyra.arqui.msproductos.repository.ProductoRepository;

@Service
public class ProductoService {

  private final ProductoRepository jpaRepository;

  public ProductoService(ProductoRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  public Producto save(ProductoRequest producto) {

    Producto productoEntity = Producto.builder()
        .codigo(producto.getCodigo())
        .nombre(producto.getNombre())
        .descripcion(producto.getDescripcion())
        .precio(producto.getPrecio())
        .stock(producto.getStock())
        .build();

    jpaRepository.save(productoEntity);

    return productoEntity;
  }

  public Page<Producto> findAll(Pageable pageable) {
    return jpaRepository.findAll(pageable);
  }

  public Optional<Producto> findById(Long id) {
    return jpaRepository.findById(id);

  }

}

package com.neyra.arqui.msproductos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neyra.arqui.msproductos.dto.ProductoRequest;
import com.neyra.arqui.msproductos.model.Producto;
import com.neyra.arqui.msproductos.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping
  public Page<Producto> getAll(Pageable pageable) {
    return productoService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
    return productoService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> create(@RequestBody ProductoRequest entity) {

    Producto producto = productoService.save(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(producto);
  }

}

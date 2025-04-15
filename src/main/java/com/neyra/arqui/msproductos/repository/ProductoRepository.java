package com.neyra.arqui.msproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neyra.arqui.msproductos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
  // No additional methods are needed for basic CRUD operations

}

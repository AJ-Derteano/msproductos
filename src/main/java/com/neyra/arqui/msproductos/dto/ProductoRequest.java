package com.neyra.arqui.msproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

  private String codigo;
  private String nombre;
  private String descripcion;
  private String precio;
  private int stock;
}

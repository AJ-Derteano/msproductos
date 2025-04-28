package com.neyra.arqui.msproductos.model;

import java.io.Serializable;
import java.util.Objects;

public class EmailNotification implements Serializable {
  private String to;
  private String subject;
  private String body;

  public EmailNotification() {
  }

  public EmailNotification(String to, String subject, String body) {
    this.to = to;
    this.subject = subject;
    this.body = body;
  }

  public static EmailNotification forNewProduct(Producto producto, String email) {
    String subject = "Nuevo producto disponible: " + producto.getNombre();
    String body = String.format(
        "¡Hola! Un nuevo producto ha sido agregado a nuestro catálogo:\n\n" +
            "Código: %s\n" +
            "Nombre: %s\n" +
            "Descripción: %s\n" +
            "Precio: %s\n" +
            "Stock: %d\n\n" +
            "¡No te lo pierdas!",
        producto.getCodigo(),
        producto.getNombre(),
        producto.getDescripcion(),
        producto.getPrecio(),
        producto.getStock()

    );

    return new EmailNotification(email, subject, body);
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EmailNotification that = (EmailNotification) o;
    return Objects.equals(to, that.to) &&
        Objects.equals(subject, that.subject) &&
        Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(to, subject, body);
  }

  @Override
  public String toString() {
    return "EmailNotification{" +
        "to='" + to + '\'' +
        ", subject='" + subject + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}

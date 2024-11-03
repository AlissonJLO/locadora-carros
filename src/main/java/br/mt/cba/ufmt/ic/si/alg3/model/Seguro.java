package br.mt.cba.ufmt.ic.si.alg3.model;

import java.math.BigDecimal;

public class Seguro {
  private Long id;
  private String tipoSeguro;
  private BigDecimal valorSeguro;
  private Long carroId;

  public Seguro() {
  }

  public Seguro(String tipoSeguro, BigDecimal valorSeguro, Long carroId) {
    this.tipoSeguro = tipoSeguro;
    this.valorSeguro = valorSeguro;
    this.carroId = carroId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipoSeguro() {
    return tipoSeguro;
  }

  public void setTipoSeguro(String tipoSeguro) {
    this.tipoSeguro = tipoSeguro;
  }

  public BigDecimal getValorSeguro() {
    return valorSeguro;
  }

  public void setValorSeguro(BigDecimal valorSeguro) {
    this.valorSeguro = valorSeguro;
  }

  public Long getCarroId() {
    return carroId;
  }

  public void setCarroId(Long carroId) {
    this.carroId = carroId;
  }

  // Getters e Setters
}

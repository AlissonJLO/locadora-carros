package br.mt.cba.ufmt.ic.si.alg3.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Multa {
  private Long id;
  private BigDecimal valor;
  private LocalDate dataMulta;
  private String descricao;
  private Long reservaId;

  public Multa() {
  }

  public Multa(BigDecimal valor, LocalDate dataMulta, String descricao, Long reservaId) {
    this.valor = valor;
    this.dataMulta = dataMulta;
    this.descricao = descricao;
    this.reservaId = reservaId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public LocalDate getDataMulta() {
    return dataMulta;
  }

  public void setDataMulta(LocalDate dataMulta) {
    this.dataMulta = dataMulta;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Long getReservaId() {
    return reservaId;
  }

  public void setReservaId(Long reservaId) {
    this.reservaId = reservaId;
  }

  // Getters e Setters
}

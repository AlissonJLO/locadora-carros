package br.mt.cba.ufmt.ic.si.alg3.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Manutencao {
  private Long id;
  private LocalDate dataManutencao;
  private String descricao;
  private BigDecimal valor;
  private Long carroId;

  public Manutencao() {
  }

  public Manutencao(LocalDate dataManutencao, String descricao, BigDecimal valor, Long carroId) {
    this.dataManutencao = dataManutencao;
    this.descricao = descricao;
    this.valor = valor;
    this.carroId = carroId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDataManutencao() {
    return dataManutencao;
  }

  public void setDataManutencao(LocalDate dataManutencao) {
    this.dataManutencao = dataManutencao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public Long getCarroId() {
    return carroId;
  }

  public void setCarroId(Long carroId) {
    this.carroId = carroId;
  }

  // Getters e Setters
}

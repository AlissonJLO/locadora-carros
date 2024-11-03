package br.mt.cba.ufmt.ic.si.alg3.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {
    private Long id;
    private LocalDate dataReserva;
    private LocalDate dataDevolucao;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private Long clienteId;
    private Long carroId;

    public Reserva() {
    }

    public Reserva(LocalDate dataReserva, LocalDate dataDevolucao, BigDecimal valor, LocalDate dataPagamento,
            Long clienteId, Long carroId) {
        this.dataReserva = dataReserva;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.clienteId = clienteId;
        this.carroId = carroId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    // Getters e Setters
}

package br.mt.cba.ufmt.ic.si.alg3.model;

public class Carro {
    private Long id;
    private String modelo;
    private String fabricante;
    private Integer ano;
    private String placa;
    private Boolean disponibilidade;

    public Carro() {}

    public Carro(String modelo, String fabricante, Integer ano, String placa, Boolean disponibilidade) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.placa = placa;
        this.disponibilidade = disponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    // Getters e Setters
}

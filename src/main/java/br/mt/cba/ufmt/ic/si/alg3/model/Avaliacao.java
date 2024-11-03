package br.mt.cba.ufmt.ic.si.alg3.model;

public class Avaliacao {
    private Long id;
    private Integer nota;
    private String comentario;
    private Long clienteId;
    private Long carroId;

    public Avaliacao() {
    }

    public Avaliacao(Integer nota, String comentario, Long clienteId, Long carroId) {
        this.nota = nota;
        this.comentario = comentario;
        this.clienteId = clienteId;
        this.carroId = carroId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

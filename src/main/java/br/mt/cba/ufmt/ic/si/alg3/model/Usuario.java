package br.mt.cba.ufmt.ic.si.alg3.model;

public class Usuario {
  private int id;
  private String nome;
  private String login;
  private String senha;
  private String nivelAcesso;

  public Usuario() {
  }

  public Usuario(String nome, String login, String senha, String nivelAcesso) {
    this.nome = nome;
    this.login = login;
    this.senha = senha;
    this.nivelAcesso = nivelAcesso;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNivelAcesso() {
    return nivelAcesso;
  }

  public void setNivelAcesso(String nivelAcesso) {
    this.nivelAcesso = nivelAcesso;
  }
}

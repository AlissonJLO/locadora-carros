package br.mt.cba.ufmt.ic.si.alg3.repository;

import br.mt.cba.ufmt.ic.si.alg3.model.Usuario;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

  // Método para inserir um novo usuário
  // Ajuste para usar Long
  public void inserir(Usuario usuario) {
    String sql = "INSERT INTO usuario (nome, login, senha, nivel_acesso) VALUES (?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, usuario.getNome());
      stmt.setString(2, usuario.getLogin());
      stmt.setString(3, usuario.getSenha()); // A senha deve ser hash
      stmt.setString(4, usuario.getNivelAcesso());

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          usuario.setId(generatedKeys.getLong(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir o usuário", e);
    }
  }

  // Método para atualizar um usuário existente
  public void atualizar(Usuario usuario) {
    String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, nivel_acesso = ? WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setString(1, usuario.getNome());
      stmt.setString(2, usuario.getLogin());
      stmt.setString(3, usuario.getSenha());
      stmt.setString(4, usuario.getNivelAcesso());
      stmt.setLong(5, usuario.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar o usuário", e);
    }
  }

  // Método para excluir um usuário pelo ID
  public void excluir(int id) {
    String sql = "DELETE FROM usuario WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setInt(1, id);

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao excluir o usuário", e);
    }
  }

  // Método para buscar um usuário pelo ID
  public Usuario buscarPorId(int id) {
    String sql = "SELECT * FROM usuario WHERE id = ?";
    Usuario usuario = null;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setInt(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          usuario = new Usuario();
          usuario.setId(rs.getLong("id"));
          usuario.setNome(rs.getString("nome"));
          usuario.setLogin(rs.getString("login"));
          usuario.setSenha(rs.getString("senha"));
          usuario.setNivelAcesso(rs.getString("nivel_acesso"));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar o usuário", e);
    }

    return usuario;
  }

  // Método para listar todos os usuários
  public List<Usuario> listarTodos() {
    String sql = "SELECT * FROM usuario";
    List<Usuario> usuarios = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setNivelAcesso(rs.getString("nivel_acesso"));
        usuarios.add(usuario);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar os usuários", e);
    }

    return usuarios;
  }
}

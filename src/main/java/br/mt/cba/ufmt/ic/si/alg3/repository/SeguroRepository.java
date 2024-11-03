package br.mt.cba.ufmt.ic.si.alg3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.mt.cba.ufmt.ic.si.alg3.model.Seguro;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

public class SeguroRepository {

  // Método para inserir um novo seguro
  public void inserir(Seguro seguro) {
    String sql = "INSERT INTO seguro (tipo_seguro, valor_seguro, carro_id) VALUES (?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, seguro.getTipoSeguro());
      if (seguro.getValorSeguro() != null) {
        stmt.setBigDecimal(2, seguro.getValorSeguro());
      } else {
        stmt.setNull(2, Types.NUMERIC);
      }
      stmt.setLong(3, seguro.getCarroId());

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          seguro.setId(generatedKeys.getLong(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir o seguro", e);
    }
  }

  // Método para atualizar um seguro existente
  public void atualizar(Seguro seguro) {
    String sql = "UPDATE seguro SET tipo_seguro = ?, valor_seguro = ?, carro_id = ? WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setString(1, seguro.getTipoSeguro());
      if (seguro.getValorSeguro() != null) {
        stmt.setBigDecimal(2, seguro.getValorSeguro());
      } else {
        stmt.setNull(2, Types.NUMERIC);
      }
      stmt.setLong(3, seguro.getCarroId());
      stmt.setLong(4, seguro.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar o seguro", e);
    }
  }

  // Método para excluir um seguro pelo ID
  public void excluir(long id) {
    String sql = "DELETE FROM seguro WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao excluir o seguro", e);
    }
  }

  // Método para buscar um seguro pelo ID
  public Seguro buscarPorId(long id) {
    String sql = "SELECT * FROM seguro WHERE id = ?";
    Seguro seguro = null;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          seguro = new Seguro();
          seguro.setId(rs.getLong("id"));
          seguro.setTipoSeguro(rs.getString("tipo_seguro"));
          seguro.setValorSeguro(rs.getBigDecimal("valor_seguro"));
          seguro.setCarroId(rs.getLong("carro_id"));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar o seguro", e);
    }

    return seguro;
  }

  // Método para listar todos os seguros
  public List<Seguro> listarTodos() {
    String sql = "SELECT * FROM seguro";
    List<Seguro> seguros = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Seguro seguro = new Seguro();
        seguro.setId(rs.getLong("id"));
        seguro.setTipoSeguro(rs.getString("tipo_seguro"));
        seguro.setValorSeguro(rs.getBigDecimal("valor_seguro"));
        seguro.setCarroId(rs.getLong("carro_id"));
        seguros.add(seguro);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar os seguros", e);
    }

    return seguros;
  }
}

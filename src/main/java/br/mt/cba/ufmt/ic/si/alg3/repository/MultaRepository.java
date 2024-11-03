package br.mt.cba.ufmt.ic.si.alg3.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.mt.cba.ufmt.ic.si.alg3.model.Multa;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

public class MultaRepository {

  // Método para inserir uma nova multa
  public void inserir(Multa multa) {
    String sql = "INSERT INTO multa (valor, data_multa, descricao, reserva_id) VALUES (?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      if (multa.getValor() != null) {
        stmt.setBigDecimal(1, multa.getValor());
      } else {
        stmt.setNull(1, Types.NUMERIC);
      }
      stmt.setDate(2, Date.valueOf(multa.getDataMulta()));
      stmt.setString(3, multa.getDescricao());
      stmt.setLong(4, multa.getReservaId());

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          multa.setId(generatedKeys.getLong(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir a multa", e);
    }
  }

  // Método para atualizar uma multa existente
  public void atualizar(Multa multa) {
    String sql = "UPDATE multa SET valor = ?, data_multa = ?, descricao = ?, reserva_id = ? WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      if (multa.getValor() != null) {
        stmt.setBigDecimal(1, multa.getValor());
      } else {
        stmt.setNull(1, Types.NUMERIC);
      }
      stmt.setDate(2, Date.valueOf(multa.getDataMulta()));
      stmt.setString(3, multa.getDescricao());
      stmt.setLong(4, multa.getReservaId());
      stmt.setLong(5, multa.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar a multa", e);
    }
  }

  // Método para excluir uma multa pelo ID
  public void excluir(long id) {
    String sql = "DELETE FROM multa WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao excluir a multa", e);
    }
  }

  // Método para buscar uma multa pelo ID
  public Multa buscarPorId(long id) {
    String sql = "SELECT * FROM multa WHERE id = ?";
    Multa multa = null;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          multa = new Multa();
          multa.setId(rs.getLong("id"));
          multa.setValor(rs.getBigDecimal("valor"));
          multa.setDataMulta(rs.getDate("data_multa").toLocalDate());
          multa.setDescricao(rs.getString("descricao"));
          multa.setReservaId(rs.getLong("reserva_id"));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar a multa", e);
    }

    return multa;
  }

  // Método para listar todas as multas
  public List<Multa> listarTodos() {
    String sql = "SELECT * FROM multa";
    List<Multa> multas = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Multa multa = new Multa();
        multa.setId(rs.getLong("id"));
        multa.setValor(rs.getBigDecimal("valor"));
        multa.setDataMulta(rs.getDate("data_multa").toLocalDate());
        multa.setDescricao(rs.getString("descricao"));
        multa.setReservaId(rs.getLong("reserva_id"));
        multas.add(multa);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar as multas", e);
    }

    return multas;
  }
}

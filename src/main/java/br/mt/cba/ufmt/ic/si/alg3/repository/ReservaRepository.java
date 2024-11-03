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

import br.mt.cba.ufmt.ic.si.alg3.model.Reserva;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

public class ReservaRepository {

  // Método para inserir uma nova reserva
  public void inserir(Reserva reserva) {
    String sql = "INSERT INTO reserva (data_reserva, data_devolucao, valor, data_pagamento, cliente_id, carro_id) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setDate(1, Date.valueOf(reserva.getDataReserva()));
      if (reserva.getDataDevolucao() != null) {
        stmt.setDate(2, Date.valueOf(reserva.getDataDevolucao()));
      } else {
        stmt.setNull(2, Types.DATE);
      }
      if (reserva.getValor() != null) {
        stmt.setBigDecimal(3, reserva.getValor());
      } else {
        stmt.setNull(3, Types.NUMERIC);
      }
      if (reserva.getDataPagamento() != null) {
        stmt.setDate(4, Date.valueOf(reserva.getDataPagamento()));
      } else {
        stmt.setNull(4, Types.DATE);
      }
      stmt.setLong(5, reserva.getClienteId());
      stmt.setLong(6, reserva.getCarroId());

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          reserva.setId(generatedKeys.getLong(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir a reserva", e);
    }
  }

  // Método para atualizar uma reserva existente
  public void atualizar(Reserva reserva) {
    String sql = "UPDATE reserva SET data_reserva = ?, data_devolucao = ?, valor = ?, data_pagamento = ?, cliente_id = ?, carro_id = ? WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setDate(1, Date.valueOf(reserva.getDataReserva()));
      if (reserva.getDataDevolucao() != null) {
        stmt.setDate(2, Date.valueOf(reserva.getDataDevolucao()));
      } else {
        stmt.setNull(2, Types.DATE);
      }
      if (reserva.getValor() != null) {
        stmt.setBigDecimal(3, reserva.getValor());
      } else {
        stmt.setNull(3, Types.NUMERIC);
      }
      if (reserva.getDataPagamento() != null) {
        stmt.setDate(4, Date.valueOf(reserva.getDataPagamento()));
      } else {
        stmt.setNull(4, Types.DATE);
      }
      stmt.setLong(5, reserva.getClienteId());
      stmt.setLong(6, reserva.getCarroId());
      stmt.setLong(7, reserva.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar a reserva", e);
    }
  }

  // Método para excluir uma reserva pelo ID
  public void excluir(long id) {
    String sql = "DELETE FROM reserva WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao excluir a reserva", e);
    }
  }

  // Método para buscar uma reserva pelo ID
  public Reserva buscarPorId(long id) {
    String sql = "SELECT * FROM reserva WHERE id = ?";
    Reserva reserva = null;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          reserva = new Reserva();
          reserva.setId(rs.getLong("id"));
          reserva.setDataReserva(rs.getDate("data_reserva").toLocalDate());
          Date dataDevolucao = rs.getDate("data_devolucao");
          if (dataDevolucao != null) {
            reserva.setDataDevolucao(dataDevolucao.toLocalDate());
          }
          reserva.setValor(rs.getBigDecimal("valor"));
          Date dataPagamento = rs.getDate("data_pagamento");
          if (dataPagamento != null) {
            reserva.setDataPagamento(dataPagamento.toLocalDate());
          }
          reserva.setClienteId(rs.getLong("cliente_id"));
          reserva.setCarroId(rs.getLong("carro_id"));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar a reserva", e);
    }

    return reserva;
  }

  // Método para listar todas as reservas
  public List<Reserva> listarTodos() {
    String sql = "SELECT * FROM reserva";
    List<Reserva> reservas = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("id"));
        reserva.setDataReserva(rs.getDate("data_reserva").toLocalDate());
        Date dataDevolucao = rs.getDate("data_devolucao");
        if (dataDevolucao != null) {
          reserva.setDataDevolucao(dataDevolucao.toLocalDate());
        }
        reserva.setValor(rs.getBigDecimal("valor"));
        Date dataPagamento = rs.getDate("data_pagamento");
        if (dataPagamento != null) {
          reserva.setDataPagamento(dataPagamento.toLocalDate());
        }
        reserva.setClienteId(rs.getLong("cliente_id"));
        reserva.setCarroId(rs.getLong("carro_id"));
        reservas.add(reserva);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar as reservas", e);
    }

    return reservas;
  }
}

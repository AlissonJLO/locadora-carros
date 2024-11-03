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

import br.mt.cba.ufmt.ic.si.alg3.model.Manutencao;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

public class ManutencaoRepository {

    // Método para inserir uma nova manutenção
    public void inserir(Manutencao manutencao) {
        String sql = "INSERT INTO manutencao (data_manutencao, descricao, valor, carro_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(2, manutencao.getDescricao());
            if (manutencao.getValor() != null) {
                stmt.setBigDecimal(3, manutencao.getValor());
            } else {
                stmt.setNull(3, Types.NUMERIC);
            }
            stmt.setLong(4, manutencao.getCarroId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    manutencao.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a manutenção", e);
        }
    }

    // Método para atualizar uma manutenção existente
    public void atualizar(Manutencao manutencao) {
        String sql = "UPDATE manutencao SET data_manutencao = ?, descricao = ?, valor = ?, carro_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(2, manutencao.getDescricao());
            if (manutencao.getValor() != null) {
                stmt.setBigDecimal(3, manutencao.getValor());
            } else {
                stmt.setNull(3, Types.NUMERIC);
            }
            stmt.setLong(4, manutencao.getCarroId());
            stmt.setLong(5, manutencao.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a manutenção", e);
        }
    }

    // Método para excluir uma manutenção pelo ID
    public void excluir(long id) {
        String sql = "DELETE FROM manutencao WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a manutenção", e);
        }
    }

    // Método para buscar uma manutenção pelo ID
    public Manutencao buscarPorId(long id) {
        String sql = "SELECT * FROM manutencao WHERE id = ?";
        Manutencao manutencao = null;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    manutencao = new Manutencao();
                    manutencao.setId(rs.getLong("id"));
                    manutencao.setDataManutencao(rs.getDate("data_manutencao").toLocalDate());
                    manutencao.setDescricao(rs.getString("descricao"));
                    manutencao.setValor(rs.getBigDecimal("valor"));
                    manutencao.setCarroId(rs.getLong("carro_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a manutenção", e);
        }

        return manutencao;
    }

    // Método para listar todas as manutenções
    public List<Manutencao> listarTodos() {
        String sql = "SELECT * FROM manutencao";
        List<Manutencao> manutencoes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Manutencao manutencao = new Manutencao();
                manutencao.setId(rs.getLong("id"));
                manutencao.setDataManutencao(rs.getDate("data_manutencao").toLocalDate());
                manutencao.setDescricao(rs.getString("descricao"));
                manutencao.setValor(rs.getBigDecimal("valor"));
                manutencao.setCarroId(rs.getLong("carro_id"));
                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as manutenções", e);
        }

        return manutencoes;
    }
}

package br.mt.cba.ufmt.ic.si.alg3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.mt.cba.ufmt.ic.si.alg3.model.Avaliacao;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

public class AvaliacaoRepository {

    // Método para inserir uma nova avaliação
    public void inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (nota, comentario, cliente_id, carro_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setLong(3, avaliacao.getClienteId());
            stmt.setLong(4, avaliacao.getCarroId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    avaliacao.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a avaliação", e);
        }
    }

    // Método para atualizar uma avaliação existente
    public void atualizar(Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET nota = ?, comentario = ?, cliente_id = ?, carro_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setLong(3, avaliacao.getClienteId());
            stmt.setLong(4, avaliacao.getCarroId());
            stmt.setLong(5, avaliacao.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a avaliação", e);
        }
    }

    // Método para excluir uma avaliação pelo ID
    public void excluir(long id) {
        String sql = "DELETE FROM avaliacao WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a avaliação", e);
        }
    }

    // Método para buscar uma avaliação pelo ID
    public Avaliacao buscarPorId(long id) {
        String sql = "SELECT * FROM avaliacao WHERE id = ?";
        Avaliacao avaliacao = null;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    avaliacao = new Avaliacao();
                    avaliacao.setId(rs.getLong("id"));
                    avaliacao.setNota(rs.getInt("nota"));
                    avaliacao.setComentario(rs.getString("comentario"));
                    avaliacao.setClienteId(rs.getLong("cliente_id"));
                    avaliacao.setCarroId(rs.getLong("carro_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a avaliação", e);
        }

        return avaliacao;
    }

    // Método para listar todas as avaliações
    public List<Avaliacao> listarTodos() {
        String sql = "SELECT * FROM avaliacao";
        List<Avaliacao> avaliacoes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setId(rs.getLong("id"));
                avaliacao.setNota(rs.getInt("nota"));
                avaliacao.setComentario(rs.getString("comentario"));
                avaliacao.setClienteId(rs.getLong("cliente_id"));
                avaliacao.setCarroId(rs.getLong("carro_id"));
                avaliacoes.add(avaliacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as avaliações", e);
        }

        return avaliacoes;
    }
}

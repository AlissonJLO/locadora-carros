package br.mt.cba.ufmt.ic.si.alg3.repository;

import br.mt.cba.ufmt.ic.si.alg3.model.Carro;
import br.mt.cba.ufmt.ic.si.alg3.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepository {

    // Método para inserir um novo carro
    public void inserir(Carro carro) {
        String sql = "INSERT INTO carro (modelo, fabricante, ano, placa, disponibilidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getFabricante());
            if (carro.getAno() != null) {
                stmt.setInt(3, carro.getAno());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, carro.getPlaca());
            stmt.setBoolean(5, carro.getDisponibilidade());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carro.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir o carro", e);
        }
    }

    // Método para atualizar um carro existente
    public void atualizar(Carro carro) {
        String sql = "UPDATE carro SET modelo = ?, fabricante = ?, ano = ?, placa = ?, disponibilidade = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getFabricante());
            if (carro.getAno() != null) {
                stmt.setInt(3, carro.getAno());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, carro.getPlaca());
            stmt.setBoolean(5, carro.getDisponibilidade());
            stmt.setLong(6, carro.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o carro", e);
        }
    }

    // Método para excluir um carro pelo ID
    public void excluir(long id) {
        String sql = "DELETE FROM carro WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o carro", e);
        }
    }

    // Método para buscar um carro pelo ID
    public Carro buscarPorId(long id) {
        String sql = "SELECT * FROM carro WHERE id = ?";
        Carro carro = null;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    carro = new Carro();
                    carro.setId(rs.getLong("id"));
                    carro.setModelo(rs.getString("modelo"));
                    carro.setFabricante(rs.getString("fabricante"));
                    carro.setAno(rs.getInt("ano"));
                    carro.setPlaca(rs.getString("placa"));
                    carro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o carro", e);
        }

        return carro;
    }

    // Método para listar todos os carros
    public List<Carro> listarTodos() {
        String sql = "SELECT * FROM carro";
        List<Carro> carros = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getLong("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setFabricante(rs.getString("fabricante"));
                carro.setAno(rs.getInt("ano"));
                carro.setPlaca(rs.getString("placa"));
                carro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os carros", e);
        }

        return carros;
    }
}

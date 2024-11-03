package br.mt.cba.ufmt.ic.si.alg3.utils;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnection {

    private DatabaseConnection() {
        throw new IllegalStateException("Utility class");
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/locadora_carros";

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    // Método para obter uma nova conexão
    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warning("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

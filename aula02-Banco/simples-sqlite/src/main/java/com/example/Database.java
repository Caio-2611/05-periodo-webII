package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Adicione esta importação

public class Database {
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoa (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nome TEXT NOT NULL, " +
                     "idade INTEGER NOT NULL, " +
                     "cpf TEXT NOT NULL UNIQUE)";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) { // Substitua 'var' por 'Statement'
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PessoaDAO {
    public void salvar(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, idade, cpf) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setInt(2, pessoa.getIdade());
            pstmt.setString(3, pessoa.getCpf());
            pstmt.executeUpdate();
            System.out.println("Pessoa salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar pessoa: " + e.getMessage());
        }
    }
}
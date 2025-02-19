package com.example;

public class Main {
    public static void main(String[] args) {
        // Cria a tabela no banco de dados
        Database.criarTabela();

        // Cria uma nova pessoa
        Pessoa pessoa = new Pessoa("João Silva", 30, "123.456.789-00");

        // Salva a pessoa no banco de dados
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.salvar(pessoa);

        System.out.println("Cadastro realizado com sucesso!");
    }
}
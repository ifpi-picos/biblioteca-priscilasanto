package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private final String url = "jdbc:postgresql://localhost:5432/biblioteca"; // Verifique se a porta e o nome do banco de dados estão corretos
    private final String usuario = "postgres";
    private final String senha = "Aluno520?";

    public Connection conectarBanco(){
        try {
            Connection connection = DriverManager.getConnection(url, usuario, senha);

            if (connection != null) {
                System.out.println("Banco conectado com sucesso!");
                return connection;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }
}
